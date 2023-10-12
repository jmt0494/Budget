package com.taubel.budget.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taubel.budget.dtos.UserDto;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.UserAlreadyExistsException;
import com.taubel.budget.exceptions.UsernameNotFoundException;
import com.taubel.budget.repos.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public User getUserByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);
        
        if (user.isPresent()) return user.get();
        else throw new UsernameNotFoundException(username + "not found");

    }

    public UserDto register(User user) //throws UserAlreadyExistsException 
    {
        Optional<User> newUser = userRepository.findByUsername(user.getUsername());
        if (newUser.isPresent()) throw new UserAlreadyExistsException("Username Already Exists"); 
        newUser = userRepository.findByEmail(user.getEmail());
        if (newUser.isPresent()) throw new UserAlreadyExistsException("Email Already Exists");
        user.setPassword(encoder.encode(user.getPassword()));
        return new UserDto(userRepository.save(user));
    }
}
