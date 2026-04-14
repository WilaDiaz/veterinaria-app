package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Mascota;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByEspecieContainingIgnoreCase(String especie);
    List<Mascota> findByUbicacionContainingIgnoreCase(String ubicacion);
    List<Mascota> findByGeneroContainingIgnoreCase(String genero);
}
