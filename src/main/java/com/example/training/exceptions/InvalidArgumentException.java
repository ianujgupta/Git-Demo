package com.example.training.exceptions;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String msg){
        super(msg);
    }
}
