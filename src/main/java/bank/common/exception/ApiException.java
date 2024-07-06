package bank.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends Exception {
    private boolean success;
    private String message;
    private Object data;

    public ApiException(){}

    public ApiException(boolean success, String message, Object data) {
        super(message);
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
