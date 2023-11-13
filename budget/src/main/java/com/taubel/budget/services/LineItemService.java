package com.taubel.budget.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.Dtos.LineItemDto;
import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.Category;
import com.taubel.budget.entities.LineItem;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.UserNotAllowedException;
import com.taubel.budget.repos.CategoryRepository;
import com.taubel.budget.repos.LineItemRepository;

@Service
public class LineItemService {

    @Autowired
    private LineItemRepository lineItemRepo;
    
    @Autowired
    private UserService userService;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private CategoryRepository catRepo;

    public List<LineItemDto> getLineItemsByBudget(String username, Long budgetId) {
        User user = userService.findByUsername(username);
        Budget budget = budgetService.findByIdAndUsername(budgetId, user);

        List<LineItem> lineItems = lineItemRepo.findLineItemsByBudget(budget);
        List<LineItemDto> dtos = lineItems.stream()
            .map(LineItemDto::new)
            .toList();
        
        return dtos;

    }

    public LineItemDto updateCreateLineItem(LineItemDto lineItemDto, String username) {
        User user = userService.findByUsername(username);
        if (user.getId() != lineItemDto.getUserId()) throw new UserNotAllowedException(username + " does not own lineitem " + lineItemDto.getId());

        Category category = catRepo.getReferenceById(lineItemDto.getCategoryId());
        
        LineItem lineItem;
        if (lineItemDto.getId() == null) lineItem = new LineItem();
        else lineItem = lineItemRepo.getReferenceById(lineItemDto.getId());

        lineItem.setBudgetedAmount(lineItemDto.getBudgetedAmount());
        lineItem.setName(lineItemDto.getName());
        lineItem.setUser(user);
        lineItem.setCategory(category);

        LineItem updatededLineItem = lineItemRepo.save(lineItem);
        return new LineItemDto(updatededLineItem);
    }

}
