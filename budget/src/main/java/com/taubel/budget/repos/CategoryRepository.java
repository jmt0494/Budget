package com.taubel.budget.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taubel.budget.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
