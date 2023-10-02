package com.taubel.budget.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taubel.budget.dtos.UserDto;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.UserAlreadyExistsException;
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

    public UserDto register(User user) throws UserAlreadyExistsException {
        Optional<User> newUser = userRepository.findByUsername(user.getUsername());
        if (newUser.isPresent()) throw new UserAlreadyExistsException("Username Already Exists"); 
        newUser = userRepository.findByEmail(user.getEmail());
        if (newUser.isPresent()) throw new UserAlreadyExistsException("Email Already Exists");
        return new UserDto(userRepository.save(user));
    }
}
