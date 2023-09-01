package com.taubel.budget.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.entities.User;
import com.taubel.budget.repos.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
