package com.taubel.budget.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.Dtos.CategoryDto;
import com.taubel.budget.exceptions.NullFieldNotAllowedException;
import com.taubel.budget.exceptions.ResourceAlreadyExistsException;
import com.taubel.budget.services.CategoryService;

@RestController
@RequestMapping("/{username}/category")
@PreAuthorize("#username == authentication.principal.username")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

        @GetMapping("/{budget}")
    public ResponseEntity<List<CategoryDto>> getCategorysByBudget (@PathVariable("username") String username, @PathVariable("budget") Long budgetId){
        List<CategoryDto> categories = categoryService.getCategorysByBudget(username, budgetId);
        return ResponseEntity.ok(categories);
    }

    @PostMapping()
    public ResponseEntity<CategoryDto> createNewCategory(@PathVariable("username") String username, @RequestBody CategoryDto category) {   
        if (category.getId() != null) throw new ResourceAlreadyExistsException("ID field not allowed");
        CategoryDto newCategory = categoryService.updateCreateCategory(category, username);
        return ResponseEntity.ok(newCategory);
    }

    @PutMapping()
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("username") String username, @RequestBody CategoryDto category) {
        if (category.getId() == null) throw new NullFieldNotAllowedException("Category is missing an ID");
        CategoryDto updatedCategory = categoryService.updateCreateCategory(category, username);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{category}")
    public ResponseEntity<String> deleteCategory(@PathVariable("username") String username, @PathVariable("category") Long categoryId) {
        categoryService.deleteCategory(categoryId, username);
        return ResponseEntity.ok("Category " + categoryId + " deleted.");
    }
}
