package com.taubel.budget.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.dtos.BudgetDto;
import com.taubel.budget.entities.Budget;

@RestController
@RequestMapping("/{username}/budget")
@PreAuthorize("#username == authentication.principal.username")
public class BudgetController {
    
    //All methods will alter the budget as well as any dependent data (category, LineItem, Transaction)
    
    @GetMapping("/{month}/{year}")
    public ResponseEntity<BudgetDto> getBudget(@PathVariable("username") String username, @PathVariable("month") String month, @PathVariable("year") int year) {
        return ResponseEntity.ok(new BudgetDto()); //TODO retrieve from database
    }
    
    @PostMapping()
    public ResponseEntity<BudgetDto> createBudget(@PathVariable("username") String username, @RequestBody BudgetDto budget) {
        return ResponseEntity.ok(new BudgetDto()); //TODO
    }

    @DeleteMapping()
    public ResponseEntity<BudgetDto> deleteBudget(@PathVariable("username") String username, @RequestBody BudgetDto budget) {
        return ResponseEntity.ok(null); //TODO
    }


}
