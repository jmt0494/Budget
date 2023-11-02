package com.taubel.budget.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taubel.budget.entities.LineItem;

public interface LineItemRepository extends JpaRepository<LineItem, Long>{
    
}
