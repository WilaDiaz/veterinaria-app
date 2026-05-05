package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.Model;

import com.example.demo.model.Mascota;
import com.example.demo.repository.MascotaRepository;

class CatalogoControllerTest {

    @Test
    void verCatalogoCargaMascotasYRetornaVistaCatalogo() {
        MascotaRepository repository = mock(MascotaRepository.class);
        Model model = mock(Model.class);

        when(repository.findAll()).thenReturn(List.of(new Mascota()));

        CatalogoController controller = new CatalogoController();
        ReflectionTestUtils.setField(controller, "mascotaRepository", repository);

        String vista = controller.verCatalogo(model);

        assertEquals("catalogo", vista);
        verify(model).addAttribute(eq("mascotas"), anyList());
        verify(repository).findAll();
    }

    @Test
    void buscarCatalogoPorEspecie() {
        MascotaRepository repository = mock(MascotaRepository.class);
        Model model = mock(Model.class);

        when(repository.findByEspecieContainingIgnoreCase("perro")).thenReturn(List.of(new Mascota()));

        CatalogoController controller = new CatalogoController();
        ReflectionTestUtils.setField(controller, "mascotaRepository", repository);

        String vista = controller.buscarCatalogo("perro", "", "", model);

        assertEquals("catalogo", vista);
        verify(repository).findByEspecieContainingIgnoreCase("perro");
        verify(model).addAttribute(eq("mascotas"), anyList());
    }

    @Test
    void buscarCatalogoPorUbicacion() {
        MascotaRepository repository = mock(MascotaRepository.class);
        Model model = mock(Model.class);

        when(repository.findByUbicacionContainingIgnoreCase("Santiago")).thenReturn(List.of(new Mascota()));

        CatalogoController controller = new CatalogoController();
        ReflectionTestUtils.setField(controller, "mascotaRepository", repository);

        String vista = controller.buscarCatalogo("", "Santiago", "", model);

        assertEquals("catalogo", vista);
        verify(repository).findByUbicacionContainingIgnoreCase("Santiago");
        verify(model).addAttribute(eq("mascotas"), anyList());
    }

    @Test
    void buscarCatalogoPorGenero() {
        MascotaRepository repository = mock(MascotaRepository.class);
        Model model = mock(Model.class);

        when(repository.findByGeneroContainingIgnoreCase("hembra")).thenReturn(List.of(new Mascota()));

        CatalogoController controller = new CatalogoController();
        ReflectionTestUtils.setField(controller, "mascotaRepository", repository);

        String vista = controller.buscarCatalogo("", "", "hembra", model);

        assertEquals("catalogo", vista);
        verify(repository).findByGeneroContainingIgnoreCase("hembra");
        verify(model).addAttribute(eq("mascotas"), anyList());
    }

    @Test
    void buscarCatalogoSinFiltrosCargaTodo() {
        MascotaRepository repository = mock(MascotaRepository.class);
        Model model = mock(Model.class);

        when(repository.findAll()).thenReturn(List.of(new Mascota()));

        CatalogoController controller = new CatalogoController();
        ReflectionTestUtils.setField(controller, "mascotaRepository", repository);

        String vista = controller.buscarCatalogo("", "", "", model);

        assertEquals("catalogo", vista);
        verify(repository).findAll();
        verify(model).addAttribute(eq("mascotas"), anyList());
    }
}