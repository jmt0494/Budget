package com.taubel.budget.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taubel.budget.Dtos.UserDto;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.UserAlreadyExistsException;
import com.taubel.budget.exceptions.UsernameNotFoundException;
import com.taubel.budget.repos.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private UserRepository userRepo;
    private PasswordEncoder encoder;


    public UserDto getUserByUsername(String username) {

        User user = findByUsername(username);
        UserDto userDto = new UserDto(user);

        return userDto;

    }

    public UserDto register(User user)
    {
        Optional<User> existingUser = userRepo.findByUsername(user.getUsername());
        if (existingUser.isPresent()) throw new UserAlreadyExistsException("Username Already Exists"); 
        existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser.isPresent()) throw new UserAlreadyExistsException("Email Already Exists");

        User newUser = new User();
        newUser.setPassword(encoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser = userRepo.save(newUser);

        UserDto userDto = new UserDto(newUser);

        return userDto;
    }

    protected boolean UserMatchesAuth(String UrlUsername, User user) {
        String username = user.getUsername();
        boolean output = UrlUsername.equals(username);
        return output;
    }

    protected User findByUsername(String username) {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        User user;
        if (optionalUser.isPresent()) user = optionalUser.get();
        else throw new UsernameNotFoundException(username + " not found");

        return user;
    }
}
