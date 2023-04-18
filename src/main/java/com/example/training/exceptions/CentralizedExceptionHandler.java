package com.example.training.exceptions;

import com.example.training.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralizedExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleStudentNotFound(ProductNotFoundException e) {
        String msg = e.getMessage();
        return msg;
    }

    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleInvalidArgument(InvalidArgumentException e) {
        String msg = e.getMessage();
        return msg;
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserNotFoundException(UserNotFoundException e) {
        String msg = e.getMessage();
        return msg;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleUserNotFoundException(AccessDeniedException e) {
        String msg = e.getMessage();
        return new ResponseEntity(msg ,HttpStatus.UNAUTHORIZED);
    }
}