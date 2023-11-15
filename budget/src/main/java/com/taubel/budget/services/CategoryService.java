package com.taubel.budget.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.Dtos.CategoryDto;
import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.Category;
import com.taubel.budget.entities.User;
import com.taubel.budget.repos.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private UserService userService;

    public List<CategoryDto> getCategorysByBudget(String username, long budgetId) {
        User user = userService.findByUsername(username);
        Budget budget = budgetService.findByIdAndUser(budgetId, user);

        List<Category> categories = categoryRepo.findCategoryByBudget(budget);

        List<CategoryDto> categoryDtos = categories.stream()
            .map(CategoryDto::new)
            .toList();

        return categoryDtos;
    }

}
