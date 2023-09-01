package com.taubel.budget.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taubel.budget.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Method to find a user by username
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findByUsername(@Param("username") String username);
}
