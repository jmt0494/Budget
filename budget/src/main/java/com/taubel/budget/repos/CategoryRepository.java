package com.taubel.budget.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    public List<Category> findCategoryByBudget(Budget budget);
}
