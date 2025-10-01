package com.techbank.cqrscore.exceptions;

public class ConcurrencyException extends  RuntimeException{
    public ConcurrencyException(String message) {
        super(message);
    }
}
