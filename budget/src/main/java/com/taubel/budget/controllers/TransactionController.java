package com.taubel.budget.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.dtos.TransactionDto;
import com.taubel.budget.entities.Transaction;
import com.taubel.budget.services.TransactionService;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping()
    public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable("username") String username) {
        List<Transaction> transactions = transService.findTransactionsByUsername(username);
        List<TransactionDto> transactionDtos = transactions.stream()
            .map(TransactionDto::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(transactionDtos);
    }
    
    @GetMapping("/unassigned")
    public ResponseEntity<List<TransactionDto>> getUnassignedTransactions(@PathVariable("username") String username) {
        List<Transaction> transactions = transService.findUnassigedTransactions(username);
        List<TransactionDto> transactionDtos = transactions.stream()
            .map(TransactionDto::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(transactionDtos); 
    }

    @PostMapping()
    public ResponseEntity<TransactionDto> createNewTransaction(@RequestBody Transaction trans, @PathVariable("username") String username) {   
        Transaction newTransaction = transService.createNewTransaction(trans, username);
        return ResponseEntity.ok(new TransactionDto(newTransaction));
    }

    @PutMapping()
    public ResponseEntity<TransactionDto> updateTransaction(@RequestBody Transaction trans, @PathVariable("username") String username) {
        Transaction transaction = transService.updateTransaction(trans, username);
        return ResponseEntity.ok(new TransactionDto(transaction));
    }

    @DeleteMapping("/{transaction}")
    public ResponseEntity<TransactionDto> deleteTransaction(@PathVariable("transaction") long transId, @PathVariable("username") String username) {
         transService.deleteTransaction(transId, username);
         return ResponseEntity.ok(null);
    }

    
}
