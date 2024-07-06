package bank.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class NoResourceFoundException extends ClientRequestException{
    public NoResourceFoundException(String message, Map<String, Object> data){
        super(message, HttpStatus.NOT_FOUND, data);
    }

    public NoResourceFoundException(String message){
        super(message, HttpStatus.NOT_FOUND);
    }
}
