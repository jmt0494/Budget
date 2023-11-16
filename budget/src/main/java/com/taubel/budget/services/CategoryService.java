package com.taubel.budget.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.Dtos.CategoryDto;
import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.Category;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.UserNotAllowedException;
import com.taubel.budget.repos.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private UserService userService;

    public List<CategoryDto> getCategorysByBudget(String username, Long budgetId) {
        User user = userService.findByUsername(username);
        Budget budget = budgetService.findByIdAndUser(budgetId, user);

        List<Category> categories = categoryRepo.findCategoryByBudget(budget);

        List<CategoryDto> categoryDtos = categories.stream()
            .map(CategoryDto::new)
            .toList();

        return categoryDtos;
    }

    public CategoryDto updateCreateCategory(CategoryDto categoryDto, String username) {
        User user = userService.findByUsername(username);
        if (user.getId() != categoryDto.getUserId()) throw new UserNotAllowedException(username + " does not own that category");

        Budget budget = budgetService.findByIdAndUser(categoryDto.getBudgetId(), user);

        Category category;
        if (categoryDto.getId() == null) category = new Category();
        else category = categoryRepo.getReferenceById(categoryDto.getId());

        category.setName(categoryDto.getName());
        category.setBudget(budget);
        category.setUser(user);

        category = categoryRepo.save(category);

        return new CategoryDto(category);
    }

    public void deleteCategory(Long categoryId, String username) {
        Optional<Category> category = categoryRepo.findById(categoryId); 

        if (category.isPresent()) {
            boolean userMatchesAuth = userService.UserMatchesAuth(username, category.get().getUser());

            if(!userMatchesAuth) throw new UserNotAllowedException("User " + username + " does not own category " + categoryId);
        }

        categoryRepo.deleteById(categoryId);
    }
    
}
