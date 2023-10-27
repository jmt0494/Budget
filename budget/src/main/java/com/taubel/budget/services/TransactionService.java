package com.taubel.budget.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.entities.Transaction;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.TransactionAlreadyExistsException;
import com.taubel.budget.exceptions.TransactionDoesNotExistException;
import com.taubel.budget.exceptions.UserNotAllowedException;
import com.taubel.budget.repos.TransactionRepository;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private UserService userService;

    public List<Transaction> findTransactionsByUsername(String username) {
        User user = userService.getUserByUsername(username);
        return transRepo.findAllByUserId(user.getUserId());
    }

    public List<Transaction> findUnassigedTransactions(String username) {
        User user = userService.getUserByUsername(username);
        return transRepo.findTransactionsWithNullLineItemAndUserId(user.getUserId());
    }

    public Transaction updateTransaction(Transaction trans, String username) {
        boolean transDoesNotExists = trans.getId() == null;
        if (transDoesNotExists) throw new TransactionDoesNotExistException("Transaction does not exist.");

        if (!userService.UserMatchesURL(username, trans.getUser())) throw new UserNotAllowedException("User " + username + " does not own transaction " + trans.getId());
        return transRepo.save(trans);
    }

    public Transaction createNewTransaction(Transaction trans, String username) {
        boolean transAlreadyExists = trans.getId() != null;
        if (transAlreadyExists) throw new TransactionAlreadyExistsException("Cannot create transaction as transaction " + trans.getId() + " it already exists.");

        
        if (!userService.UserMatchesURL(username, trans.getUser())) throw new UserNotAllowedException("User " + username + " does not own transaction " + trans.getId());
        return transRepo.save(trans);
    }

    
    public void deleteTransaction(long transId, String username) {
        Optional<Transaction> trans = transRepo.findById(transId);
        boolean trasnIsPresent = trans.isPresent();
        
        if (trasnIsPresent){
            boolean UserMatchesURL = userService.UserMatchesURL(username, trans.get().getUser());

            if (trasnIsPresent && !UserMatchesURL){
                throw new UserNotAllowedException("User " + username + " does not own transaction " + transId);
            }
        }
        transRepo.deleteById(transId);
    }


}
