package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MascotaTest {

    @Test
    void testMascotaGettersSetters() {

        Mascota mascota = new Mascota();

        mascota.setNombre("Luna");
        mascota.setEspecie("Perro");
        mascota.setRaza("Labrador");
        mascota.setEdad(3);
        mascota.setGenero("Hembra");
        mascota.setUbicacion("Santiago");
        mascota.setEstadoAdopcion("Disponible");
        mascota.setFotoUrl("foto.jpg");

        assertEquals("Luna", mascota.getNombre());
        assertEquals("Perro", mascota.getEspecie());
        assertEquals("Labrador", mascota.getRaza());
        assertEquals(3, mascota.getEdad());
        assertEquals("Hembra", mascota.getGenero());
        assertEquals("Santiago", mascota.getUbicacion());
        assertEquals("Disponible", mascota.getEstadoAdopcion());
        assertEquals("foto.jpg", mascota.getFotoUrl());
    }
}

