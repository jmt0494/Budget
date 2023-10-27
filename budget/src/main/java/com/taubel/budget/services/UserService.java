package com.taubel.budget.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taubel.budget.Dtos.UserRegistrationDto;
import com.taubel.budget.entities.User;
import com.taubel.budget.exceptions.UserAlreadyExistsException;
import com.taubel.budget.exceptions.UsernameNotFoundException;
import com.taubel.budget.repos.UserRepository;
import com.taubel.budget.security.SecurityProfile;
import com.taubel.budget.security.SecurityProfileRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
    private UserRepository userRepository;
    private SecurityProfileRepository secProfRepo;
    private PasswordEncoder encoder;


    public User getUserByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);
        
        if (user.isPresent()) return user.get();
        else throw new UsernameNotFoundException(username + "not found");

    }

    public User register(UserRegistrationDto user)
    {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        Optional<SecurityProfile> secProf = secProfRepo.findByUsername(null);
        if (existingUser.isPresent() || secProf.isPresent()) throw new UserAlreadyExistsException("Username Already Exists"); 
        existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) throw new UserAlreadyExistsException("Email Already Exists");

        SecurityProfile newSecProf = new SecurityProfile();
        newSecProf.setUsername(user.getUsername());
        newSecProf.setPassword(encoder.encode(user.getPassword()));
        secProfRepo.save(newSecProf);

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        return userRepository.save(newUser);
    }

    protected boolean UserMatchesURL(String UrlUsername, User user) {
        String username = user.getUsername();
        boolean output = UrlUsername.equals(username);
        return output;
    }
}
