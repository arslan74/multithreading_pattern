package com.arslanhiader.www.exceptions;

public class ConsumerException extends RuntimeException{
    public ConsumerException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
