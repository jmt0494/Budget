package com.taubel.budget.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.User;

import java.util.Optional;

import com.taubel.budget.enums.Month;


public interface BudgetRepository extends JpaRepository<Budget, Long>{
    
    Optional<Budget> findByUserAndMonthAndYear(User user, Month month, int year);
}
