package com.taubel.budget.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taubel.budget.entities.Budget;
import com.taubel.budget.entities.User;

import java.util.Optional;
import java.util.List;




public interface BudgetRepository extends JpaRepository<Budget, Long>{
    
    Optional<Budget> findByIdAndUser(Long id, User user);

    List<Budget> findAllByUser(User user);

    @Query("SELECT b FROM Budget b WHERE b.user = :user AND b.year = (SELECT MAX(b2.year) FROM Budget b2 WHERE b2.user = :user)")
    List<Budget> findBudgetsForMostRecentYear(@Param("user") User user);

}
