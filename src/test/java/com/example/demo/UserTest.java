package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class UserTest {

    @Test
    void testGettersAndSetters() {
        User user = new User();

        user.setId(1);
        user.setUsername("admin");
        user.setEmail("admin@test.com");
        user.setPassword("admin123");

        assertEquals(1, user.getId());
        assertEquals("admin", user.getUsername());
        assertEquals("admin@test.com", user.getEmail());
        assertEquals("admin123", user.getPassword());
    }

    @Test
    void testUserDetailsMethods() {
        User user = new User();

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        assertNotNull(authorities);
        assertTrue(authorities.isEmpty());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }
}