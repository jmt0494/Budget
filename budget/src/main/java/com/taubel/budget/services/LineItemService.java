package com.taubel.budget.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.Dtos.LineItemDto;
import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.LineItem;
import com.taubel.budget.entities.User;
import com.taubel.budget.repos.LineItemRepository;

@Service
public class LineItemService {

    @Autowired
    private LineItemRepository lineItemRepo;
    
    @Autowired
    private UserService userService;

    @Autowired
    private BudgetService budgetService;

    public List<LineItemDto> getLineItemsByBudget(String username, Long budgetId) {
        User user = userService.findByUsername(username);
        Budget budget = budgetService.findByIdAndUsername(budgetId, user);

        List<LineItem> lineItems = lineItemRepo.findLineItemsByBudget(budget);
        List<LineItemDto> dtos = lineItems.stream()
            .map(LineItemDto::new)
            .toList();
        
        return dtos;

    }

}
