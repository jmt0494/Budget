package com.taubel.budget.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.dtos.UserDto;
import com.taubel.budget.services.UserService;

@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/{username}")
    public UserDto user(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);

    }
}
