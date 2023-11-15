package com.taubel.budget.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException{
    public ResourceAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
