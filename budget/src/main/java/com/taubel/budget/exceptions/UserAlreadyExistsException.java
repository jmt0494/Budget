package com.taubel.budget.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
