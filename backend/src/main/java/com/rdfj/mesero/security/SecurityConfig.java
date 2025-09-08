package com.rdfj.mesero.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter; // 👈 Asegúrate de tener un filtro JWT implementado

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // deshabilitamos CSRF
                .cors(cors -> {}) // habilitamos CORS (por defecto)
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**", "/bar/crear").permitAll() // autenticacion publica
                    .anyRequest().authenticated() // el resto requiere autenticación
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // añadimos nuestro filtro JWT
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}