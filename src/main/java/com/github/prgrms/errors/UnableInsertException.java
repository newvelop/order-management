package com.github.prgrms.errors;

public class UnableInsertException extends RuntimeException {
    public UnableInsertException(String message) {
        super(message);
    }

    public UnableInsertException(String message, Throwable cause) {
        super(message, cause);
    }
}
