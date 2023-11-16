package com.taubel.budget.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.Dtos.BudgetDto;
import com.taubel.budget.services.BudgetService;

@RestController
@RequestMapping("/{username}/budget")
@PreAuthorize("#username == authentication.principal.username")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;
    
    @GetMapping()
    public ResponseEntity<List<BudgetDto>> getBudgets(@PathVariable("username") String username) {
        List<BudgetDto> budgets = budgetService.findBudgetsByUsername(username);
        return ResponseEntity.ok(budgets);
    }
    
    @PostMapping()
    public ResponseEntity<BudgetDto> createBudget(@PathVariable("username") String username, @RequestBody BudgetDto budget) {
        return ResponseEntity.ok(new BudgetDto()); //TODO
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BudgetDto> deleteBudget(@PathVariable("username") String username, @PathVariable("id") Long id) {
        return ResponseEntity.ok(null); //TODO
    }


}
