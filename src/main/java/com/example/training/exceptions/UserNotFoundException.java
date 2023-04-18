package com.example.training.exceptions;

public class UserNotFoundException extends RuntimeException {

        public UserNotFoundException(String msg){
            super(msg);
        }
}
