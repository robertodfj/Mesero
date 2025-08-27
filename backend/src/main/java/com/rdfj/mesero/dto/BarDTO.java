package com.rdfj.mesero.dto;

import java.util.ArrayList;
import java.util.List;


public class BarDTO {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private List<MesaDTO> mesas = new ArrayList<>();
    private List<UsuarioDTO> usuarios = new ArrayList<>();

     // Constructor

    public BarDTO(){

    }

    public BarDTO(int id, String nombre, String direccion, 
                String telefono, List<MesaDTO> mesas, List<UsuarioDTO> usuarios){

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

    public List<MesaDTO> getMesas() {
        return mesas;
    }

    public void setMesas(List<MesaDTO> mesas) {
        this.mesas = mesas;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDTO> usuarios) {
        this.usuarios = usuarios;
    }
}
