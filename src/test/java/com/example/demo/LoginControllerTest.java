package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.security.JWTAuthtenticationConfig;

class LoginControllerTest {

    @Test
    void loginConCredencialesCorrectasRetornaToken() {
        JWTAuthtenticationConfig jwtConfig = mock(JWTAuthtenticationConfig.class);
        UserDetailsService userDetailsService = mock(UserDetailsService.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(userDetailsService.loadUserByUsername("admin")).thenReturn(userDetails);
        when(userDetails.getPassword()).thenReturn("passwordEncriptada");
        when(passwordEncoder.matches("admin123", "passwordEncriptada")).thenReturn(true);
        when(jwtConfig.getJWTToken("admin")).thenReturn("token-generado");

        LoginController controller = new LoginController();

        org.springframework.test.util.ReflectionTestUtils.setField(controller, "jwtAuthtenticationConfig", jwtConfig);
        org.springframework.test.util.ReflectionTestUtils.setField(controller, "userDetailsService", userDetailsService);
        org.springframework.test.util.ReflectionTestUtils.setField(controller, "passwordEncoder", passwordEncoder);

        String token = controller.login("admin", "admin123");

        assertEquals("token-generado", token);
    }

    @Test
    void loginConPasswordIncorrectaLanzaError() {
        JWTAuthtenticationConfig jwtConfig = mock(JWTAuthtenticationConfig.class);
        UserDetailsService userDetailsService = mock(UserDetailsService.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(userDetailsService.loadUserByUsername("admin")).thenReturn(userDetails);
        when(userDetails.getPassword()).thenReturn("passwordEncriptada");
        when(passwordEncoder.matches("malaClave", "passwordEncriptada")).thenReturn(false);

        LoginController controller = new LoginController();

        org.springframework.test.util.ReflectionTestUtils.setField(controller, "jwtAuthtenticationConfig", jwtConfig);
        org.springframework.test.util.ReflectionTestUtils.setField(controller, "userDetailsService", userDetailsService);
        org.springframework.test.util.ReflectionTestUtils.setField(controller, "passwordEncoder", passwordEncoder);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            controller.login("admin", "malaClave");
        });

        assertEquals("Invalid login", exception.getMessage());
    }
}