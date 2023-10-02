package com.taubel.budget.exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
