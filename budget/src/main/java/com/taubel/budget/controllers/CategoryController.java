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

import com.taubel.budget.entities.Category;

@RestController
@RequestMapping("/{username}/category")
@PreAuthorize("#username == authentication.principal.username")
public class CategoryController {

    @PostMapping()
    public ResponseEntity<Category> createNewCategory(@PathVariable("username") String username, @RequestBody Category category) {   
        return ResponseEntity.ok(new Category()); //TODO
    }

    @PutMapping()
    public ResponseEntity<Category> updateCategory(@PathVariable("username") String username, @RequestBody Category category) {
        return ResponseEntity.ok(new Category()); //TODO
    }

    @DeleteMapping("/{category}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("username") String username, @PathVariable("category") int categoryId) {
        return ResponseEntity.ok(null); //TODO
    }
}
