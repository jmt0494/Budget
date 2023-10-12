package com.taubel.budget.exceptions;

public class TransactionAlreadyExistsException extends RuntimeException{
    
    public TransactionAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
