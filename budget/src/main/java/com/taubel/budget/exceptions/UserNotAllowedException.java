package com.taubel.budget.exceptions;

public class UserNotAllowedException extends RuntimeException{
    public UserNotAllowedException(String errorMessage) {
        super(errorMessage);
    }
}
