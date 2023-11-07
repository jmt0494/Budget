package com.taubel.budget.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.Dtos.LineItemDto;
import com.taubel.budget.Dtos.TransactionDto;
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

    @GetMapping("/{month}/{year}")
    public ResponseEntity<List<TransactionDto>> getTransactionsByBudget(@PathVariable("username") String username, @PathVariable("month") String month, @PathVariable("year") int year) {
        List<TransactionDto> transactions = transService.findTransactionsByBudget(username, month, year);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping()
    public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable("username") String username) {
        List<TransactionDto> transactions = transService.findTransactionsByUsername(username);
        return ResponseEntity.ok(transactions);
    }
    
    @GetMapping("/unassigned")
    public ResponseEntity<List<TransactionDto>> getUnassignedTransactions(@PathVariable("username") String username) {
        List<TransactionDto> transactions = transService.findUnassigedTransactions(username);
        return ResponseEntity.ok(transactions); 
    }

    @PostMapping()
    public ResponseEntity<TransactionDto> createNewTransaction(@RequestBody TransactionDto trans, @PathVariable("username") String username) { 
        TransactionDto newTransaction = transService.createNewTransaction(trans, username);
        return ResponseEntity.ok(newTransaction);
    }

    @PutMapping()
    public ResponseEntity<TransactionDto> updateTransaction(@RequestBody TransactionDto trans, @PathVariable("username") String username) {
        TransactionDto transaction = transService.updateTransaction(trans, username);
        return ResponseEntity.ok(transaction);
    }

    @DeleteMapping("/{transaction}")
    public ResponseEntity<TransactionDto> deleteTransaction(@PathVariable("transaction") long transId, @PathVariable("username") String username) {
         transService.deleteTransaction(transId, username);
         return ResponseEntity.ok(null);
    }

}
