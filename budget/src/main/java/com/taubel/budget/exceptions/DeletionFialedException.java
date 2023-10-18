package com.taubel.budget.exceptions;

public class DeletionFialedException extends RuntimeException{
    public DeletionFialedException(String errorMessage) {
        super(errorMessage);
    }
}
