package com.rdfj.mesero.dto;

import com.rdfj.mesero.entity.Usuario.Rol;

public class UsuarioDTO {

    private int id;
    private String nombre;
    private String email;
    private BarDTO bar;
    private Rol rol;

    // Constructores
    public UsuarioDTO() {}

    public UsuarioDTO(int id, String nombre, String email, BarDTO bar, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.bar = bar;
        this.rol = rol;
    }

    // Getters y setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public BarDTO getBar() {
        return bar;
    }
    public void setBar(BarDTO bar) {
        this.bar = bar;
    }

    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}