package com.taubel.budget.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.dtos.CategoryDto;
import com.taubel.budget.entities.Category;

@RestController
@RequestMapping("/{username}/category")
@PreAuthorize("#username == authentication.principal.username")
public class CategoryController {

    @PostMapping()
    public ResponseEntity<CategoryDto> createNewCategory(@PathVariable("username") String username, @RequestBody CategoryDto category) {   
        return ResponseEntity.ok(new CategoryDto()); //TODO
    }

    @PutMapping()
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("username") String username, @RequestBody CategoryDto category) {
        return ResponseEntity.ok(new CategoryDto()); //TODO
    }

    @DeleteMapping("/{category}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable("username") String username, @PathVariable("category") int categoryId) {
        return ResponseEntity.ok(null); //TODO
    }
}
