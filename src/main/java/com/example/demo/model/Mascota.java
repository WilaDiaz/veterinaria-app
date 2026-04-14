package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity

public class Mascota {


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String nombre;
private String especie;
private String raza;
private int edad;
private String genero;
private String ubicacion;
private String estadoAdopcion;
private String fotoUrl;

public Mascota() {
}

public Long getId() {
    return id;
}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
        this.nombre = nombre;
}

public String getEspecie() {
    return especie;
}

public void setEspecie(String especie) {
        this.especie = especie;
}


public String getRaza() {
    return raza;
}
public void setRaza(String raza) {
        this.raza = raza;
}

public int getEdad() {
    return edad;
}
public void setEdad(int edad) {
        this.edad = edad;
}

public String getGenero() {
    return genero;
}
public void setGenero(String genero) {
        this.genero = genero;
}

public String getUbicacion() {
    return ubicacion;
}
public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
}

public String getEstadoAdopcion() {
    return estadoAdopcion;
}
public void setEstadoAdopcion(String estadoAdopcion) {
        this.estadoAdopcion = estadoAdopcion;
}

public String getFotoUrl() {
    return fotoUrl;
}

public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
}

}
