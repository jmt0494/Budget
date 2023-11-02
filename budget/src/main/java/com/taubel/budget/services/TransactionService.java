package com.taubel.budget.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.Dtos.TransactionDto;
import com.taubel.budget.entities.LineItem;
import com.taubel.budget.entities.Transaction;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.TransactionAlreadyExistsException;
import com.taubel.budget.exceptions.TransactionDoesNotExistException;
import com.taubel.budget.exceptions.UserNotAllowedException;
import com.taubel.budget.repos.LineItemRepository;
import com.taubel.budget.repos.TransactionRepository;
import com.taubel.budget.repos.UserRepository;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private LineItemRepository lineItemRepo;

    public List<TransactionDto> findTransactionsByUsername(String username) {
        User user = userService.getUserByUsername(username);
        List<Transaction> transactions = transRepo.findAllByUserId(user.getId());
        List<TransactionDto> dtos = transactions.stream()
            .map(TransactionDto::new)
            .toList();

        return dtos;
    }

    public List<TransactionDto> findUnassigedTransactions(String username) {
        User user = userService.getUserByUsername(username);
        List<Transaction> transactions = transRepo.findTransactionsWithNullLineItemAndUserId(user.getId());
        List<TransactionDto> dtos = transactions.stream()
            .map(TransactionDto::new)
            .toList();

        return dtos;
    }

    public TransactionDto updateTransaction(TransactionDto transDto, String username) {

        boolean transDoesNotExists = transDto.getId() == null;
        if (transDoesNotExists) throw new TransactionDoesNotExistException("Transaction does not exist.");

        User transactionsUser = userRepo.getReferenceById(transDto.getUserId());
        if (!userService.UserMatchesURL(username, transactionsUser)) throw new UserNotAllowedException("User " + username + " does not own transaction " + transDto.getId());

        Transaction trans = transRepo.getReferenceById(transDto.getId());

        trans.setAmount(transDto.getAmount());
        trans.setMerchant(transDto.getMerchant());
        trans.setTransactionDate(transDto.getTransactionDate());
        if (trans.getLineItem() != null) {
            LineItem lineitem = lineItemRepo.getReferenceById(transDto.getLineItemId());
            trans.setLineItem(lineitem);
        }

        Transaction updatedTransaction = transRepo.save(trans);
        TransactionDto updatedDto = new TransactionDto(updatedTransaction);

        return updatedDto;
    }

    public TransactionDto createNewTransaction(TransactionDto trans, String username) {
        boolean transAlreadyExists = trans.getId() != null;
        if (transAlreadyExists) throw new TransactionAlreadyExistsException("Cannot create transaction as transaction " + trans.getId() + " it already exists.");

        
        // if (!userService.UserMatchesURL(username, trans.getUser())) throw new UserNotAllowedException("User " + username + " does not own transaction " + trans.getId());
        // transRepo.save(trans);

        return new TransactionDto();
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
