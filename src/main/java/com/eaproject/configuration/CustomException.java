package com.eaproject.configuration;

public class CustomException extends RuntimeException {
    private final String requestBody;

    public CustomException(String message, String requestBody) {
        super(message);
        this.requestBody = requestBody;
    }

    public String getRequestBody() {
        return requestBody;
    }
}
