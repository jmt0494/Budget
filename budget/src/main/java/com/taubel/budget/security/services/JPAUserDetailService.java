package com.taubel.budget.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taubel.budget.repos.UserRepository;
import com.taubel.budget.security.SecurityUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JPAUserDetailService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var u = userRepository.findByUsername(username);
        return u.map(SecurityUser::new)
            .orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));
    }
    
}
