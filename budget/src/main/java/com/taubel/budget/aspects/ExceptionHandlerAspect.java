package com.taubel.budget.aspects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taubel.budget.exceptions.BudgetNotFoundException;
import com.taubel.budget.exceptions.LineItemAlreadyExistsException;
import com.taubel.budget.exceptions.NullFieldNotAllowedException;
import com.taubel.budget.exceptions.TransactionAlreadyExistsException;
import com.taubel.budget.exceptions.TransactionDoesNotExistException;
import com.taubel.budget.exceptions.UserAlreadyExistsException;
import com.taubel.budget.exceptions.UserNotAllowedException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerAspect {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> UserAlreadyExistsHandler(HttpServletRequest request, UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

        @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> IllegalArgumentExceptionHandler(HttpServletRequest request, IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TransactionAlreadyExistsException.class)
    public ResponseEntity<Object> TransactionAlreadyExistsHandler(HttpServletRequest request, TransactionAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(TransactionDoesNotExistException.class)
    public ResponseEntity<Object> TransactionDoesNotExistHandler(HttpServletRequest request, TransactionDoesNotExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(UserNotAllowedException.class)
    public ResponseEntity<Object> UserNotAllowedHandler(HttpServletRequest request, UserNotAllowedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(BudgetNotFoundException.class)
    public ResponseEntity<Object> BudgetNotFoundHandler(HttpServletRequest request, BudgetNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(LineItemAlreadyExistsException.class)
    public ResponseEntity<Object> LineItemAlreadyExistsHandler(HttpServletRequest request, LineItemAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(NullFieldNotAllowedException.class)
        public ResponseEntity<Object> NullFieldNotAllowedHandler(HttpServletRequest request, NullFieldNotAllowedException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}