package com.taubel.budget.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.entities.Transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/transaction")
public class TransactionController {
    
    @PostMapping()
    public ResponseEntity<Transaction> createNewTransaction(@RequestBody Transaction trans) {   
        return ResponseEntity.ok(new Transaction()); //TODO
    }

    @PutMapping()
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction trans) {
        return ResponseEntity.ok(new Transaction()); //TODO
    }

    @DeleteMapping("/{transaction}")
    public ResponseEntity<Integer> deleteTransaction(@PathVariable("transaction") int transId) {
        return ResponseEntity.ok(null); //TODO
    }
    
}
