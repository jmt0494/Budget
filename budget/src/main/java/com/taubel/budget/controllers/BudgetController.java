package com.taubel.budget.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.entities.Budget;

@RestController
@RequestMapping("/{username}/budget")
public class BudgetController {
    
    //All methods with alter the budget as well as any dependent data (category, LineItem, Transaction)

    @GetMapping("/{month}")
    ResponseEntity<Budget> getBudget(@PathVariable("username") String usename, @PathVariable("month") String month) {
        return ResponseEntity.ok(new Budget()); //TODO retrieve from database
    }
    
    @PostMapping("/{month}")
    ResponseEntity<Budget> createBudget(@PathVariable("username") String usename, @PathVariable("month") String month) {
        return ResponseEntity.ok(new Budget()); //TODO
    }

    @DeleteMapping("/{month}")
    ResponseEntity<Budget> deleteBudget(@PathVariable("username") String usename, @PathVariable("month") String month) {
        return ResponseEntity.ok(null); //TODO
    }


}
