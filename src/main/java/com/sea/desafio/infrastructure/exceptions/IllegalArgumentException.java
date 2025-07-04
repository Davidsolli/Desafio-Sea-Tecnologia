package com.sea.desafio.infrastructure.exceptions;

public class IllegalArgumentException extends RuntimeException {

    public IllegalArgumentException(String msg) {
        super(msg);
    }

    public IllegalArgumentException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
