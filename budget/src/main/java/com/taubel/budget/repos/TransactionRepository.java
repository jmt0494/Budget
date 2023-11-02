package com.taubel.budget.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taubel.budget.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    
     @Query("SELECT t FROM Transaction t WHERE t.lineItem IS NULL AND t.user.id = :userId")
    List<Transaction> findTransactionsWithNullLineItemAndUserId(@Param("userId") Long userId);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId")
    List<Transaction> findAllByUserId(@Param("userId") Long userId);
    
}
