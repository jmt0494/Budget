package com.taubel.budget.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taubel.budget.dtos.UserDto;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.UsernameNotFoundException;
import com.taubel.budget.repos.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private UserRepository userRepository;

    public UserDto getUserByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        //TODO make this throw an exception and add AOP to handle it.
        if (user.isPresent()) return new UserDto(user.get());
        else return null;

    }
}
