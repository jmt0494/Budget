package com.taubel.budget.dtos;

import com.taubel.budget.entities.User;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto {

    public UserDto(User user) {
        email = user.getEmail();
        userId = user.getUserId();
        username = user.getUsername();
    }
    
    private Long userId;

    private String username;

    private String email;
}
