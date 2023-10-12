package com.taubel.budget.aspects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taubel.budget.exceptions.TransactionAlreadyExistsException;
import com.taubel.budget.exceptions.TransactionDoesNotExistException;
import com.taubel.budget.exceptions.UserAlreadyExistsException;

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
}