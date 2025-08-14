package com.rdfj.mesero.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre del bar no puede estar vacio.")
    private String nombre;

    @NotBlank(message = "La direccion del bar no puede estar vacia.")
    private String direccion;

    @NotBlank(message = "El numero de telefono no puede estar vacio.")
    @Column(unique = true)
    private String telefono;

    // Creamos un array vacio para evitar nullpointexception

    @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mesa> mesas = new ArrayList<>();

    @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL, orphanRemoval = true)
    private  List<Usuario> usuarios = new ArrayList<>();

    // Constructor

    public Bar(){

    }

    public Bar(int id, String nombre, String direccion, 
                String telefono, List<Mesa> mesas, List<Usuario> usuarios){

                    this.id = id;
                    this.nombre = nombre;
                    this.direccion = direccion;
                    this.telefono = telefono;
                    this.mesas = mesas;
                    this.usuarios = usuarios;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
