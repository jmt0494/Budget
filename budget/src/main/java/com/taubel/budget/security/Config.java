package com.taubel.budget.security;

import org.hibernate.jdbc.Expectations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {

    // @Bean
    // SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     return http
    //     .authorizeHttpRequests(auth -> auth
    //                                  .requestMatchers("/user/{username}").access("@securityService.canAccessUser(principal, #username)"))
    //         .build();
    // }

    @Bean
    PasswordEncoder passwordEncoder(){
        //TODO user an engryption
        return NoOpPasswordEncoder.getInstance();
    }

}

