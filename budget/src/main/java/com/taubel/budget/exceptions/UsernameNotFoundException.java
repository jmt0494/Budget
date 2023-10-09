package com.taubel.budget.exceptions;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    
}
