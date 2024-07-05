package com.lab9.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;
    private final String message;
    private final boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ResponseMeta meta;

    ApiResponse(String message, boolean success) {
        this.data = null;
        this.message = message;
        this.success = success;
    }

    ApiResponse(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    ApiResponse(T data, String message, boolean success, ResponseMeta meta) {
        this.data = data;
        this.message = message;
        this.success = success;
        this.meta = meta;
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message, true);
    }

    public static <T> ApiResponse<T> success(T data, String message, ResponseMeta meta) {
        return new ApiResponse<>(data, message, true, meta);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(message, false);
    }

    public static <T> ApiResponse<T> error(String message, T data) {
        return new ApiResponse<>(data, message, false);
    }
}
