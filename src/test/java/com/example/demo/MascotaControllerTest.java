package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.demo.model.Mascota;
import com.example.demo.repository.MascotaRepository;

class MascotaControllerTest {

    @Test
    void listarMascotasRetornaLista() {
        MascotaRepository repo = mock(MascotaRepository.class);
        when(repo.findAll()).thenReturn(List.of(new Mascota()));

        MascotaController controller = new MascotaController();
        ReflectionTestUtils.setField(controller, "mascotaRepository", repo);

        assertEquals(1, controller.listarMascotas().size());
        verify(repo).findAll();
    }

    @Test
    void buscarPorEspecie() {
        MascotaRepository repo = mock(MascotaRepository.class);
        when(repo.findByEspecieContainingIgnoreCase("perro")).thenReturn(List.of(new Mascota()));

        MascotaController controller = new MascotaController();
        ReflectionTestUtils.setField(controller, "mascotaRepository", repo);

        assertEquals(1, controller.buscarMascota("perro", null, null).size());
        verify(repo).findByEspecieContainingIgnoreCase("perro");
    }

    @Test
    void buscarPorUbicacion() {
        MascotaRepository repo = mock(MascotaRepository.class);
        when(repo.findByUbicacionContainingIgnoreCase("Santiago")).thenReturn(List.of(new Mascota()));

        MascotaController controller = new MascotaController();
        ReflectionTestUtils.setField(controller, "mascotaRepository", repo);

        assertEquals(1, controller.buscarMascota(null, "Santiago", null).size());
        verify(repo).findByUbicacionContainingIgnoreCase("Santiago");
    }

    @Test
    void buscarPorGenero() {
        MascotaRepository repo = mock(MascotaRepository.class);
        when(repo.findByGeneroContainingIgnoreCase("Hembra")).thenReturn(List.of(new Mascota()));

        MascotaController controller = new MascotaController();
        ReflectionTestUtils.setField(controller, "mascotaRepository", repo);

        assertEquals(1, controller.buscarMascota(null, null, "Hembra").size());
        verify(repo).findByGeneroContainingIgnoreCase("Hembra");
    }

    @Test
    void crearMascotaGuardaMascota() {
        MascotaRepository repo = mock(MascotaRepository.class);
        Mascota mascota = new Mascota();

        when(repo.save(mascota)).thenReturn(mascota);

        MascotaController controller = new MascotaController();
        ReflectionTestUtils.setField(controller, "mascotaRepository", repo);

        assertEquals(mascota, controller.crearMascota(mascota));
        verify(repo).save(mascota);
    }

    @Test
    void actualizarMascotaExistente() {
        MascotaRepository repo = mock(MascotaRepository.class);

        Mascota existente = new Mascota();
        Mascota actualizada = new Mascota();
        actualizada.setNombre("Luna");
        actualizada.setEspecie("Perro");
        actualizada.setRaza("Labrador");
        actualizada.setEdad(3);
        actualizada.setGenero("Hembra");
        actualizada.setUbicacion("Santiago");
        actualizada.setEstadoAdopcion("Disponible");
        actualizada.setFotoUrl("foto.jpg");

        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        when(repo.save(existente)).thenReturn(existente);

        MascotaController controller = new MascotaController();
        ReflectionTestUtils.setField(controller, "mascotaRepository", repo);

        Mascota resultado = controller.actualizarMascota(1L, actualizada);

        assertEquals("Luna", resultado.getNombre());
        verify(repo).findById(1L);
        verify(repo).save(existente);
    }

    @Test
    void eliminarMascotaRetornaMensaje() {
        MascotaRepository repo = mock(MascotaRepository.class);

        MascotaController controller = new MascotaController();
        ReflectionTestUtils.setField(controller, "mascotaRepository", repo);

        String respuesta = controller.eliminarMascota(1L);

        assertEquals("Mascota eliminada exitosamente", respuesta);
        verify(repo).deleteById(1L);
    }
}