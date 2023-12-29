package com.taubel.budget.security.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class JPAAuthenticationManager implements AuthenticationManager{
    @Autowired
    private UserDetailsService userDetailsService;



    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        UserDetails ud = userDetailsService.loadUserByUsername(auth.getPrincipal().toString());

        if (ud.getPassword().equals(auth.getCredentials())) {
            auth.setAuthenticated(true);
        }

        return auth;
    }
    
}
