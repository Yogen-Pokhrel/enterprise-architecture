package com.lab9;

import com.fasterxml.jackson.databind.JavaType;
import com.lab9.book.dto.request.BookFilterDto;
import com.lab9.book.dto.request.CreateBookDto;
import com.lab9.book.dto.request.UpdateBookDto;
import com.lab9.book.dto.response.BookDto;
import com.lab9.common.ApiResponse;
import com.lab9.common.exception.ApiException;
import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Client {
    private static final String BASE_URL = "http://localhost:8080/books";
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public Client() {
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public BookDto addBook(CreateBookDto createBookDto) throws Exception {
        String json = objectMapper.writeValueAsString(createBookDto);
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        return handleResponse(response, BookDto.class);
    }

    public BookDto updateBook(String isbn, UpdateBookDto updateBookDto) throws Exception {
        String json = objectMapper.writeValueAsString(updateBookDto);
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(BASE_URL + "/" + isbn)
                .put(body)
                .build();

        Response response = client.newCall(request).execute();
        return handleResponse(response, BookDto.class);
    }

    public void deleteBook(String isbn) throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + isbn)
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            handleResponse(response, Void.class);
        }
    }

    public BookDto getBook(String isbn) throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + isbn)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            return handleResponse(response, BookDto.class);
        }
    }

    public List<BookDto> getAllBooks(BookFilterDto bookFilterDto) throws Exception {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL).newBuilder();
        if (bookFilterDto.getTitle() != null) {
            urlBuilder.addQueryParameter("title", bookFilterDto.getTitle());
        }
        if (bookFilterDto.getAuthor() != null) {
            urlBuilder.addQueryParameter("author", bookFilterDto.getAuthor());
        }

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            JavaType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, BookDto.class);
            return handleResponse(response, listType);
        }
    }

    private <T> T handleResponse(Response response, Class<T> valueType) throws Exception {
        return handleResponse(response, objectMapper.getTypeFactory().constructType(valueType));
    }

    private <T> T handleResponse(Response response, JavaType valueType) throws Exception {
        if (!response.isSuccessful()) {
            String responseBody = response.body().string();
            ApiResponse<?> errorResponse = objectMapper.readValue(responseBody, ApiResponse.class);
            throw new ApiException(errorResponse.isSuccess(), errorResponse.getMessage(), errorResponse.getData());
        }
        String responseBody = response.body().string();
        ApiResponse<T> apiResponse = objectMapper.readValue(responseBody, objectMapper.getTypeFactory().constructParametricType(ApiResponse.class, valueType));
        return apiResponse.getData();
    }
}
