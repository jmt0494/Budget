package com.taubel.budget.exceptions;

public class TransactionDoesNotExistException extends RuntimeException{
    public TransactionDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }
    
}
