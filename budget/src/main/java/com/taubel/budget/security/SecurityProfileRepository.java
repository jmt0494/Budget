package com.taubel.budget.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityProfileRepository extends JpaRepository<SecurityProfile, Long> {
    Optional<SecurityProfile> findByUsername(String username);
}
