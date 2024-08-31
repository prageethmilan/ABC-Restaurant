package com.abc.restaurant.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationException extends RuntimeException {

    private int status;
    private boolean success;
    private String message;

    public ApplicationException(int status, boolean success, String message) {
        super(message);
        this.status = status;
        this.success = success;
        this.message = message;
    }
}
