package com.lab9.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ClientRequestException extends Exception{
    private final String message;
    private HttpStatus statusCode = HttpStatus.CONFLICT;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> data = new HashMap<>();

    ClientRequestException(String message){
        this.message = message;
    }

    ClientRequestException(String message, Map<String, Object> data){
        this.message = message;
        this.data = data;
    }

    ClientRequestException(String message, HttpStatus statusCode){
        this.message = message;
        this.statusCode = statusCode;
    }

    ClientRequestException(String message, HttpStatus statusCode, Map<String, Object> data){
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }
}
