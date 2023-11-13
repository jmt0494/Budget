package com.taubel.budget.exceptions;

public class NullFieldNotAllowedException extends RuntimeException {

    public NullFieldNotAllowedException(String errorMesage) {
        super(errorMesage);
    }
    
}
