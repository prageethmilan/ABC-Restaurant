package com.abc.restaurant.exception;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserException extends Throwable {
    private int status;
    private boolean success;
    private String message;

    public UserException(int status, boolean success, String message) {
        this.status = status;
        this.success = success;
        this.message = message;
    }
}
