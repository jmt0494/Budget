package com.taubel.budget.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.taubel.budget.security.filters.JwtAuthorizationFilter;
import com.taubel.budget.security.filters.UsernamePasswordFilter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class Config {

    @Value("${secret.key:sectretKey}")
    private String secrect;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(customizer -> customizer.disable())
                .cors(customizer -> customizer.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(authCustomizer -> authCustomizer
                        .requestMatchers(HttpMethod.POST,
                                "/user/register",
                                "user/login",
                                "/error")
                        .permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new UsernamePasswordFilter(authenticationManager, passwordEncoder(), secrect),
                        JwtAuthorizationFilter.class)
                .addFilter(new JwtAuthorizationFilter(authenticationManager, secrect));
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
