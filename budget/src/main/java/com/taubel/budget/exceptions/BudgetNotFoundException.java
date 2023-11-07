package com.taubel.budget.exceptions;

public class BudgetNotFoundException extends RuntimeException{
    
    public BudgetNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
