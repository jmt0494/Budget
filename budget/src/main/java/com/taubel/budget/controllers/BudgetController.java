package com.taubel.budget.controllers;

import java.util.ArrayList;
import java.util.List;

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

@RestController
@RequestMapping("/{username}/budget")
@PreAuthorize("#username == authentication.principal.username")
public class BudgetController {
    
    @GetMapping()
    public ResponseEntity<List<BudgetDto>> getBudgets(@PathVariable("username") String username) {
        return ResponseEntity.ok(new ArrayList<BudgetDto>()); //TODO retrieve from database
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetDto> getBudget(@PathVariable("username") String username, @PathVariable("id") Long id) {
        return ResponseEntity.ok(new BudgetDto());
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
