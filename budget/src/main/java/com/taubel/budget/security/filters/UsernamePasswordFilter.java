package com.taubel.budget.security.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taubel.budget.Dtos.CredentialsDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UsernamePasswordFilter extends OncePerRequestFilter {

    private long tokenExpirationDuration = 3_600_000; // 1 hour

    private String secret;

    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    public UsernamePasswordFilter(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
            String secret) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.secret = secret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        if (path.equals("/user/login")) {
            try {
                CredentialsDto creds = new ObjectMapper()
                        .readValue(request.getInputStream(), CredentialsDto.class);
                Authentication auth = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                creds.getUsername(),
                                passwordEncoder.encode(creds.getPassword()),
                                new ArrayList<>()));
                if (auth.isAuthenticated()) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    successfulAuthentication(request, response, filterChain, auth);
                }
            } catch (IOException e) {
                throw new RuntimeException("Invalid Credentials");
            }
        }
        filterChain.doFilter(request, response);
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(authResult.getPrincipal().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpirationDuration))
                .sign(Algorithm.HMAC512(secret));

        String body = "{\"token\": \"" + token + "\"}";

        response.setContentType("application/json");

        response.getWriter().write(body);
        response.getWriter().flush();
    }

}
