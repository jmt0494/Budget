package com.taubel.budget.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.entities.Transaction;
import com.taubel.budget.services.TransactionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/{username}/transaction")
@PreAuthorize("#username == authentication.principal.username")
public class TransactionController {

    @Autowired
    private TransactionService transService;
    
    @GetMapping("/unassigned")
    public ResponseEntity<List<Transaction>> getUnassignedTransactions(@PathVariable("username") String username) {
        return ResponseEntity.ok(transService.findUnassigedTransactions(username));
    }

    @PostMapping()
    public ResponseEntity<Transaction> createNewTransaction(@RequestBody Transaction trans, @PathVariable("username") String username) {   
        return ResponseEntity.ok(transService.createNewTransaction(trans, username));
    }

    @PutMapping()
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction trans, @PathVariable("username") String username) {
        return ResponseEntity.ok(transService.updateTransaction(trans, username));
    }

    @DeleteMapping("/{transaction}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable("transaction") long transId, @PathVariable("username") String username) {
         transService.deleteTransaction(transId, username);
         return ResponseEntity.ok(null);
    }

    
}
