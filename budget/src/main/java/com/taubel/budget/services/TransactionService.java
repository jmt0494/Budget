package com.taubel.budget.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.Dtos.TransactionDto;
import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.LineItem;
import com.taubel.budget.entities.Transaction;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.BudgetNotFoundException;
import com.taubel.budget.exceptions.TransactionAlreadyExistsException;
import com.taubel.budget.exceptions.TransactionDoesNotExistException;
import com.taubel.budget.exceptions.UserNotAllowedException;
import com.taubel.budget.exceptions.UsernameNotFoundException;
import com.taubel.budget.repos.BudgetRepository;
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

    @Autowired
    private BudgetRepository budgetRepo;

    @Autowired
    private BudgetService budgetService;


    public List<TransactionDto> findTransactionsByBudget(String username, Long budgetId) {
        User user = userService.findByUsername(username);
        Budget budget = budgetService.findByIdAndUsername(budgetId, user);

        List<Transaction> transactions = transRepo.findTransactionsByBudget(budget);
        List<TransactionDto> transDtos = transactions.stream()
            .map(TransactionDto::new)
            .toList();

        return transDtos;
    }

    public List<TransactionDto> findTransactionsByUsername(String username) {
        User user = userService.findByUsername(username);
        List<Transaction> transactions = transRepo.findAllByUserId(user.getId());
        List<TransactionDto> dtos = transactions.stream()
            .map(TransactionDto::new)
            .toList();

        return dtos;
    }


    public List<TransactionDto> findUnassigedTransactions(String username) {
        User user = userService.findByUsername(username);
        List<Transaction> transactions = transRepo.findTransactionsWithNullLineItemAndUserId(user.getId());
        List<TransactionDto> dtos = transactions.stream()
            .map(TransactionDto::new)
            .toList();

        return dtos;
    }


    public TransactionDto updateTransaction(TransactionDto transDto, String authUsername) {
        boolean transDoesNotExists = transDto.getId() == null;
        if (transDoesNotExists) throw new TransactionDoesNotExistException("Transaction does not exist.");

        User transactionsUser = userRepo.getReferenceById(transDto.getUserId());
        if (!userService.UserMatchesAuth(authUsername, transactionsUser)) throw new UserNotAllowedException(authUsername + " can not create transactions for other users");

        Transaction trans = transRepo.getReferenceById(transDto.getId());
        trans.setAmount(transDto.getAmount());
        trans.setMerchant(transDto.getMerchant());
        trans.setTransactionDate(transDto.getTransactionDate());
        if (trans.getLineItem() != null) {
            LineItem lineitem = lineItemRepo.getReferenceById(transDto.getLineItemId());
            trans.setLineItem(lineitem);
        }

        TransactionDto updatedDto = new TransactionDto(transRepo.save(trans));
        return updatedDto;
    }


    public TransactionDto createNewTransaction(TransactionDto transDto, String authUsername) {
        boolean transAlreadyExists = transDto.getId() != null;
        if (transAlreadyExists) throw new TransactionAlreadyExistsException("Cannot create transaction as transaction " + transDto.getId() + " already exists.");

        User transactionsUser = userRepo.getReferenceById(transDto.getUserId());
        if (!userService.UserMatchesAuth(authUsername, transactionsUser)) throw new UserNotAllowedException("User " + authUsername + " does not own transaction " + transDto.getId());

        Transaction newTrans = new Transaction();
        newTrans.setAmount(transDto.getAmount());
        newTrans.setMerchant(transDto.getMerchant());
        newTrans.setTransactionDate(transDto.getTransactionDate());
        newTrans.setUser(transactionsUser);
        if (transDto.getLineItemId() != null) {
            LineItem lineItem = lineItemRepo.getReferenceById(transDto.getLineItemId());
            newTrans.setLineItem(lineItem);
        }

        TransactionDto newTransDto = new TransactionDto(transRepo.save(newTrans));
        return newTransDto;
    }

    
    public void deleteTransaction(long transId, String username) {
        Optional<Transaction> trans = transRepo.findById(transId);
        boolean trasnIsPresent = trans.isPresent();
        
        if (trasnIsPresent){
            boolean UserMatchesURL = userService.UserMatchesAuth(username, trans.get().getUser());

            if (trasnIsPresent && !UserMatchesURL){
                throw new UserNotAllowedException("User " + username + " does not own transaction " + transId);
            }
        }
        transRepo.deleteById(transId);
    }


}
