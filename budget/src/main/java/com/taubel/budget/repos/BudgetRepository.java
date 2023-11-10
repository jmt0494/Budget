package com.taubel.budget.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.User;

import java.util.Optional;



public interface BudgetRepository extends JpaRepository<Budget, Long>{
    
    Optional<Budget> findByIdAndUser(Long id, User user);
}
