package com.example.demo.controller;

import com.example.demo.model.Mascota;
import com.example.demo.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogoController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping("/catalogo")
    public String verCatalogo(Model model) {
        model.addAttribute("mascotas", mascotaRepository.findAll());
        return "catalogo";
    }

    @GetMapping("/catalogo/buscar")
    public String buscarCatalogo(String especie, String ubicacion, String genero, Model model) {

        if (especie != null && !especie.isBlank()) {
            model.addAttribute("mascotas", mascotaRepository.findByEspecieContainingIgnoreCase(especie));
        } else if (ubicacion != null && !ubicacion.isBlank()) {
            model.addAttribute("mascotas", mascotaRepository.findByUbicacionContainingIgnoreCase(ubicacion));
        } else if (genero != null && !genero.isBlank()) {
            model.addAttribute("mascotas", mascotaRepository.findByGeneroContainingIgnoreCase(genero));
        } else {
            model.addAttribute("mascotas", mascotaRepository.findAll());
        }

        return "catalogo";
    }
}