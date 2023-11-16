package com.taubel.budget.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.Dtos.BudgetDto;
import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.BudgetNotFoundException;
import com.taubel.budget.repos.BudgetRepository;

@Service
public class BudgetService {
    
    @Autowired
    BudgetRepository budgetRepo;

    @Autowired
    UserService userService;

    public List<BudgetDto> findBudgetsByUsername(String Username) {
        User user = userService.findByUsername(Username);
        List<Budget> budgets = budgetRepo.findAllByUser(user);
        List<BudgetDto> dtos = budgets.stream()
            .map(BudgetDto::new)
            .toList();
        
        return dtos;
    }

    public void deleteBudget(Long id, String username) {
        
    }

    
    protected Budget findByIdAndUser(Long id, User user) {
        Optional<Budget> optionalBudget = budgetRepo.findByIdAndUser(id, user);
        Budget budget;
        if (optionalBudget.isPresent()) budget = optionalBudget.get();
        else throw new BudgetNotFoundException(user.getUsername() + " does not have a budget with the ID " + id);

        return budget;
    }

}
