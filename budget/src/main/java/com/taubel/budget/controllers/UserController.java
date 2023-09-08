package com.taubel.budget.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.dtos.UserDto;
import com.taubel.budget.entities.User;
import com.taubel.budget.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    public UserDto user(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);

    }

    @PostMapping(value = "/register")
    public UserDto register(@RequestBody User user) {
        return userService.register(user);
    }
}
