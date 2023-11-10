package com.taubel.budget.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.LineItem;

public interface LineItemRepository extends JpaRepository<LineItem, Long>{
    
    @Query("SELECT li FROM LineItem li WHERE li.category.budget = :budget")
    List<LineItem> findLineItemsByBudget(Budget budget);

}
