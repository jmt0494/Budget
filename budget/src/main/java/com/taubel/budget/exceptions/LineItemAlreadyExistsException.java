package com.taubel.budget.exceptions;

public class LineItemAlreadyExistsException extends RuntimeException{
    public LineItemAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
