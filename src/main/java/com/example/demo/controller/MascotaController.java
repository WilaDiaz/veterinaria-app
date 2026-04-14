package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Mascota;
import com.example.demo.repository.MascotaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping
    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    @GetMapping("/buscar")
    public List<Mascota> buscarMascota(
            @RequestParam(required = false) String especie,
            @RequestParam(required = false) String ubicacion,
            @RequestParam(required = false) String genero) {

        if (especie != null && !especie.isBlank()) {
            return mascotaRepository.findByEspecieContainingIgnoreCase(especie);
        }

        if (ubicacion != null && !ubicacion.isBlank()) {
            return mascotaRepository.findByUbicacionContainingIgnoreCase(ubicacion);
        }

        if (genero != null && !genero.isBlank()) {
            return mascotaRepository.findByGeneroContainingIgnoreCase(genero);
        }

        return mascotaRepository.findAll();
    }

    @PostMapping
    public Mascota crearMascota(@RequestBody Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @PutMapping("/{id}")
    public Mascota actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascotaActualizada) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        mascota.setNombre(mascotaActualizada.getNombre());
        mascota.setEspecie(mascotaActualizada.getEspecie());
        mascota.setRaza(mascotaActualizada.getRaza());
        mascota.setEdad(mascotaActualizada.getEdad());
        mascota.setGenero(mascotaActualizada.getGenero());
        mascota.setUbicacion(mascotaActualizada.getUbicacion());
        mascota.setEstadoAdopcion(mascotaActualizada.getEstadoAdopcion());
        mascota.setFotoUrl(mascotaActualizada.getFotoUrl());

        return mascotaRepository.save(mascota);
    }

    @DeleteMapping("/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotaRepository.deleteById(id);
        return "Mascota eliminada exitosamente";
    }
}
