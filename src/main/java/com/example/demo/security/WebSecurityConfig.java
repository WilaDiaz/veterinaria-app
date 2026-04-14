package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
            .headers(headers -> headers
                .contentTypeOptions(contentType -> {})
                )
        .authorizeHttpRequests(authz -> authz
          .requestMatchers(
                 "/",
                 "/login-page",
                "/catalogo",
                "/catalogo/buscar",
                "/admin/mascotas-view",
                "/admin/mascotas/nueva",
                "/style.css"
            ).permitAll()

        .requestMatchers(HttpMethod.POST, Constants.LOGIN_URL).permitAll()
        .requestMatchers(HttpMethod.GET, "/api/mascotas").permitAll()
        .requestMatchers(HttpMethod.GET, "/api/mascotas/buscar").permitAll()

            .anyRequest().authenticated()
)
        .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}