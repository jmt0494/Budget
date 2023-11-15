package com.taubel.budget.Dtos;

import com.taubel.budget.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public UserDto(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.id = user.getId();
    }

    private Long id;
    
    private String username;

    private String email;
    
}
