package com.taubel.budget.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.entities.Transaction;
import com.taubel.budget.entities.User;
import com.taubel.budget.repos.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository tranRepo;

    @Autowired
    private UserService userService;

    public List<Transaction> findUnassigedTransactions(String username) {
        User user = userService.getUserByUsername(username);
        return tranRepo.findTransactionsWithNullLineItemAndUserId(user.getUserId());
    }
}
