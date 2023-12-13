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

    @Query(value = "SELECT * FROM budgets " +
                   "WHERE user_id = :#{#user.id} " +
                   "ORDER BY year DESC, " +
                   "CASE " +
                   "    WHEN month = 'Dec' THEN 1 " +
                   "    WHEN month = 'Nov' THEN 2 " +
                   "    WHEN month = 'Oct' THEN 3 " +
                   "    WHEN month = 'Sep' THEN 4 " +
                   "    WHEN month = 'Aug' THEN 5 " +
                   "    WHEN month = 'Jul' THEN 6 " +
                   "    WHEN month = 'Jun' THEN 7 " +
                   "    WHEN month = 'May' THEN 8 " +
                   "    WHEN month = 'Apr' THEN 9 " +
                   "    WHEN month = 'Mar' THEN 10 " +
                   "    WHEN month = 'Feb' THEN 11 " +
                   "    WHEN month = 'Jan' THEN 12 " +
                   "END", nativeQuery = true)
    List<Budget> findAllSortedByYearAndMonthForUser(@Param("user") User user);

    @Query("SELECT b FROM Budget b WHERE b.user = :user AND b.year = (SELECT MAX(b2.year) FROM Budget b2 WHERE b2.user = :user)")
    List<Budget> findBudgetsForMostRecentYear(@Param("user") User user);

}
