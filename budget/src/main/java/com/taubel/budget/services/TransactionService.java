package com.taubel.budget.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.entities.Transaction;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.DeletionFialedException;
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

    public List<Transaction> findUnassigedTransactions(String username) {
        User user = userService.getUserByUsername(username);
        return transRepo.findTransactionsWithNullLineItemAndUserId(user.getUserId());
    }

    public Transaction updateTransaction(Transaction trans, String username) {
        boolean transDoesNotExists = trans.getId() == null;
        if (transDoesNotExists) throw new TransactionDoesNotExistException("Transaction does not exist.");

        if (!userService.UserMatchesURL(username, trans.getUser())) throw new UserNotAllowedException("User may not update transactions for other users");
        return transRepo.save(trans);
    }

    public Transaction createNewTransaction(Transaction trans, String username) {
        boolean transAlreadyExists = trans.getId() != null;
        if (transAlreadyExists) throw new TransactionAlreadyExistsException("Cannot create transaction. Transaction already exists.");

        
        if (!userService.UserMatchesURL(username, trans.getUser())) throw new UserNotAllowedException("User may not create transactions for other users");
        return transRepo.save(trans);
    }

    
        public void deleteTransaction(long transId) {
                 transRepo.deleteById(transId);
                 Optional<Transaction> deletedTrans = transRepo.findById(transId);
                 if (deletedTrans.isPresent()) throw new DeletionFialedException("Failed to delete transaction " + transId);
            }


}
