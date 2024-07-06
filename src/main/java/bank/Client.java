package bank;

import bank.common.ApiResponse;
import bank.common.exception.ApiException;
import bank.dto.request.CreateAccountDto;
import bank.dto.request.TransactionDto;
import bank.dto.request.TransferFundsDto;
import bank.dto.response.AccountDto;
import bank.dto.response.AccountListDto;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Client {
    private static final String BASE_URL = "http://localhost:8080/accounts";
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public Client() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<AccountDto> getAllAccounts() throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            JavaType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, AccountListDto.class);
            return handleResponse(response, listType);
        }
    }

    public AccountDto getAccount(Long accountNumber) throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + accountNumber)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return handleResponse(response, AccountDto.class);
        }
    }

    public AccountDto createAccount(CreateAccountDto createAccountDto) throws Exception {
        String json = objectMapper.writeValueAsString(createAccountDto);
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return handleResponse(response, AccountDto.class);
        }
    }

    public void transferFunds(TransferFundsDto transferFundsDto) throws Exception {
        String json = objectMapper.writeValueAsString(transferFundsDto);
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(BASE_URL + "/transfer")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            handleResponse(response, Void.class);
        }
    }

    public void transaction(Long accountNumber, TransactionDto transactionDto) throws Exception {
        String json = objectMapper.writeValueAsString(transactionDto);
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(BASE_URL + "/" + accountNumber)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            handleResponse(response, Void.class);
        }
    }

    private <T> T handleResponse(Response response, Class<T> valueType) throws Exception {
        return handleResponse(response, objectMapper.getTypeFactory().constructType(valueType));
    }

    private <T> T handleResponse(Response response, JavaType javaType) throws Exception {
        String responseBody = response.body().string();
        if (!response.isSuccessful()) {
            ApiResponse<?> errorResponse = objectMapper.readValue(responseBody, ApiResponse.class);
            throw new ApiException(errorResponse.isSuccess(), errorResponse.getMessage(), errorResponse.getData());
        }
        ApiResponse<T> apiResponse = objectMapper.readValue(responseBody, objectMapper.getTypeFactory().constructParametricType(ApiResponse.class, javaType));
        return apiResponse.getData();
    }
}
