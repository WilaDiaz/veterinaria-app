package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String root() {
        return "redirect:/catalogo";
    }

    @GetMapping("/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin/mascotas-view")
    public String adminMascotasView() {
        return "adminMascotas";
    }

    @GetMapping("/admin/mascotas/nueva")
    public String nuevaMascotaView() {
        return "nuevaMascota";
    }
}