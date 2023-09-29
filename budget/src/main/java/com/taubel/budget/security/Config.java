package com.taubel.budget.security;

import org.hibernate.jdbc.Expectations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Config {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // .csrf(customizer -> customizer.disable())
            .authorizeHttpRequests(authCustomizer -> authCustomizer
                .requestMatchers(HttpMethod.POST, "/user/register").permitAll() // Allow unauthenticated access to /user/register
                .requestMatchers("/**").authenticated() // Require authentication for all other requests
            );
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        //TODO user an engryption
        return NoOpPasswordEncoder.getInstance();
    }

}

