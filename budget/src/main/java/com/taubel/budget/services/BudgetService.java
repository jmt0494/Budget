package com.taubel.budget.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.Dtos.BudgetDto;
import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.Category;
import com.taubel.budget.entities.LineItem;
import com.taubel.budget.entities.Transaction;
import com.taubel.budget.entities.User;
import com.taubel.budget.enums.Month;
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
        List<Budget> budgets = budgetRepo.findAllSortedByYearAndMonthForUser(user);
        List<BudgetDto> dtos = budgets.stream()
            .map(BudgetDto::new)
            .toList();
        
        return dtos;
    }

    /**
     * Saves a new Budget entity to the database and creates dependent entities.
     * Dependents will either be a copy of the most recent budget or the default 
     * dependents if the user has no other budgets.
     * 
     * @param username
     * @param oldBudget
     * @return newBudgetDto
     */

    public BudgetDto createNewBudgetAndDependents(String username, BudgetDto oldBudget){
        User user = userService.findByUsername(username);
        Optional<Budget> mostRecentBudgetOptional = findMostRescentBudget(user);

        Budget newBudget = new Budget();
        newBudget.setMonth(oldBudget.getMonth());
        newBudget.setUser(user);
        newBudget.setYear(oldBudget.getYear());
        newBudget.setId(budgetRepo.save(newBudget).getId());

        mostRecentBudgetOptional.ifPresentOrElse(b -> copyBudgetDependents(b, newBudget), () -> addDefaultDependants(newBudget));

        BudgetDto newBudgetDto = new BudgetDto(newBudget);
        return newBudgetDto;

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
    
    protected Optional<Budget> findMostRescentBudget (User user) {
        List<Budget> mostRecentYearBudgets = budgetRepo.findBudgetsForMostRecentYear(user);
        if (!mostRecentYearBudgets.isEmpty()){
            List<Month> orderedMonths = Month.getReverseOrderedMonths();
            for(Month m : orderedMonths) {
                for(Budget b : mostRecentYearBudgets) {
                    if (b.getMonth().equals(m)) return Optional.of(b);
                }
            }
        }
        return Optional.empty();
        
    }
    
    protected Budget findByIdAndUser(Long id, User user) {
        Optional<Budget> optionalBudget = budgetRepo.findByIdAndUser(id, user);
        Budget budget;
        if (optionalBudget.isPresent()) budget = optionalBudget.get();
        else throw new BudgetNotFoundException(user.getUsername() + " does not have a budget with the ID " + id);
        
        return budget;
    }
    
    private void addDefaultDependants(Budget budget) {
        Map<String, String[]> categoryAndLineItems = new HashMap<>();
        String[] incomeLineItems = new String[] {"Paycheck 1", "Paycheck 2"};
        String[] givingLineItems = new String[] {"Church"};
        String[] housingLineItems = new String[] {"Rent/Mortage", "Water", "Electric", "Gas", "Trash"};
        String[] transportationLineItems = new String[] {"Gas", "Maintenance"};
        String[] lifeStyleLineItems = new String[] {"Fun Money", "Misc"};
        String[] foodLineItems = new String[] {"Groceries", "Resturants"};

        categoryAndLineItems.put("Income", incomeLineItems);
        categoryAndLineItems.put("Giving", givingLineItems);
        categoryAndLineItems.put("Housing", housingLineItems);
        categoryAndLineItems.put("Transportation", transportationLineItems);
        categoryAndLineItems.put("Line Style", lifeStyleLineItems);
        categoryAndLineItems.put("Food", foodLineItems);

        for (String catName : categoryAndLineItems.keySet()) {
            Category newCategory = new Category();
            newCategory.setBudget(budget);
            newCategory.setName(catName);
            newCategory.setUser(budget.getUser());
            newCategory = categoryRepo.save(newCategory);

            for(String lIName : categoryAndLineItems.get(catName)) {
                LineItem newLineItem = new LineItem();
                newLineItem.setBudgetedAmount(0);
                newLineItem.setCategory(newCategory);
                newLineItem.setName(lIName);
                newLineItem.setUser(budget.getUser());
                lineItemRepo.save(newLineItem);
            }
        }
    }
    
private void copyBudgetDependents(Budget oldBudget, Budget newBudget) {
        List<Category> categories = categoryRepo.findCategoryByBudget(oldBudget);
        List<LineItem> lineItems = lineItemRepo.findLineItemsByBudget(oldBudget);

        Map<Long, Category> categoryIdMap = new HashMap<>();
    
        categories.forEach(cat -> {
            Category copyCat = new Category();
            copyCat.setBudget(newBudget);
            copyCat.setName(cat.getName());
            copyCat.setUser(cat.getUser());
            copyCat.setId(categoryRepo.save(copyCat).getId());
            categoryIdMap.putIfAbsent(cat.getId(), copyCat);
        });

        lineItems.forEach(li -> {
            LineItem copyLineItem = new LineItem();
            copyLineItem.setBudgetedAmount(li.getBudgetedAmount());
            copyLineItem.setName(li.getName());
            copyLineItem.setUser(li.getUser());
            copyLineItem.setCategory(categoryIdMap.get(li.getCategory().getId()));
            lineItemRepo.save(copyLineItem);
            
        });
    }
}
