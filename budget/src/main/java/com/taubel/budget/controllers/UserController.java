package com.taubel.budget.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taubel.budget.Dtos.UserDto;
import com.taubel.budget.entities.User;
import com.taubel.budget.services.UserService;

@RestController

@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> user(@PathVariable("username") String username) {
        UserDto user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDto> register(@RequestBody User user) {
        UserDto newUser = userService.register(user);
        return ResponseEntity.ok(newUser);
    }

}
