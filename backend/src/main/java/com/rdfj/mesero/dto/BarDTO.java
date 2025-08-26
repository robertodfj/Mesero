package com.rdfj.mesero.dto;

import java.util.ArrayList;
import java.util.List;

import com.rdfj.mesero.dto.MesaDTO;
import com.rdfj.mesero.dto.UsuarioDTO;

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
