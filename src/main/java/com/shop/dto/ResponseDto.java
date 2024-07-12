package com.shop.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private String message;
    private Object data;
    public ResponseDto(){}
    public ResponseDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResponseDto(String message) {
        this.message = message;
        this.data = null;
    }
}
