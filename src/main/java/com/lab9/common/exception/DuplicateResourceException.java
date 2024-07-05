package com.lab9.common.exception;

import lombok.Getter;

import java.util.Map;

@Getter
public class DuplicateResourceException extends ClientRequestException {
     public DuplicateResourceException(String message, Map<String, Object> data){
        super(message, data);
    }

    public DuplicateResourceException(String message){
        super(message);
    }
}
