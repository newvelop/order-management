package com.github.prgrms.errors;

public class UnableUpdateException extends RuntimeException {
    public UnableUpdateException(String message) {
        super(message);
    }

    public UnableUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
