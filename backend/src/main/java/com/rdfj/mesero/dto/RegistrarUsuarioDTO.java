package com.rdfj.mesero.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegistrarUsuarioDTO {

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String nombre;
    
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    @NotBlank(message = "El email no puede estar vacío")
    private String email;
    
    @NotNull(message = "El rol no puede estar vacío")
    private String rol;

    @NotNull(message = "El bar no puede estar vacío")
    @Min(value = 1, message = "El barId debe ser mayor que 0")
    private Integer barId;

    // Constructores
    public RegistrarUsuarioDTO() {}

    public RegistrarUsuarioDTO(String nombre, String password, String email, String rol, Integer barId) {
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.barId = barId;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getBarId() {
        return barId;
    }

    public void setBarId(Integer barId) {
        this.barId = barId;
    }
}