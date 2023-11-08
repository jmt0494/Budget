package com.taubel.budget.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JPAUserDetailService implements UserDetailsService{

    private final SecurityProfileRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        var u = userRepository.findByUsername(username);
        return u.map(SecurityUser::new)
            .orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));
    }
    
}
