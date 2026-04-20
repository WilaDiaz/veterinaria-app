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

            .headers(headers -> headers.contentTypeOptions(contentType -> {}))

            .authorizeHttpRequests(authz -> authz
                .requestMatchers(
                    "/",
                    "/login-page",
                    "/catalogo",
                    "/catalogo/buscar",
                    "/style.css"
                ).permitAll()

                // Login web para páginas
                .requestMatchers("/do-login").permitAll()


                // Login API para JWT
                .requestMatchers(HttpMethod.POST, "/api/login").permitAll()


                // APIs públicas
                .requestMatchers(HttpMethod.GET, "/api/mascotas").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/mascotas/buscar").permitAll()


                // Páginas privadas
                .requestMatchers("/admin/**").authenticated()


                // APIs privadas
                .requestMatchers("/api/**").authenticated()

                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login-page")
                .loginProcessingUrl("/do-login")
                .defaultSuccessUrl("/admin/mascotas-view", true)
                .failureUrl("/login-page?error=true")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login-page")
                .permitAll()
            )

            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}