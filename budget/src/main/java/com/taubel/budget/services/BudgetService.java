package com.taubel.budget.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.Dtos.BudgetDto;
import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.Category;
import com.taubel.budget.entities.LineItem;
import com.taubel.budget.entities.Transaction;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.BudgetNotFoundException;
import com.taubel.budget.repos.BudgetRepository;
import com.taubel.budget.repos.CategoryRepository;
import com.taubel.budget.repos.LineItemRepository;
import com.taubel.budget.repos.TransactionRepository;

@Service
public class BudgetService {
    
    @Autowired
    private BudgetRepository budgetRepo;
    
    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private LineItemRepository lineItemRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private UserService userService;



    public List<BudgetDto> findBudgetsByUsername(String Username) {
        User user = userService.findByUsername(Username);
        List<Budget> budgets = budgetRepo.findAllByUser(user);
        List<BudgetDto> dtos = budgets.stream()
            .map(BudgetDto::new)
            .toList();
        
        return dtos;
    }

    /**
     * Deletes the budget associated with the id passed and all related depentants.
     * 
     * @param id
     * @param username
     */
    public void deleteBudget(Long id, String username) {
        User user = userService.findByUsername(username);

        Budget budget = findByIdAndUser(id, user);
        List<Transaction> transactions = transactionRepo.findTransactionsByBudget(budget);
        List<LineItem> lineItems = lineItemRepo.findLineItemsByBudget(budget);
        List<Category> categories = categoryRepo.findCategoryByBudget(budget);

        transactions.forEach(trans -> transactionRepo.delete(trans));
        lineItems.forEach(li -> lineItemRepo.delete(li));
        categories.forEach(cat -> categoryRepo.delete(cat));
        budgetRepo.delete(budget);
    }

    
    protected Budget findByIdAndUser(Long id, User user) {
        Optional<Budget> optionalBudget = budgetRepo.findByIdAndUser(id, user);
        Budget budget;
        if (optionalBudget.isPresent()) budget = optionalBudget.get();
        else throw new BudgetNotFoundException(user.getUsername() + " does not have a budget with the ID " + id);

        return budget;
    }

}
