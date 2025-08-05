package com.example.turukiazbeach.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class NoSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Allow all requests
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Disable frame restrictions
                .httpBasic(AbstractHttpConfigurer::disable) // Disable HTTP Basic
                .formLogin(AbstractHttpConfigurer::disable); // Disable login page

        return http.build();
    }
}
