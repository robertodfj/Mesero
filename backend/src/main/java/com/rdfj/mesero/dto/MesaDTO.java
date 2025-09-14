package com.rdfj.mesero.dto;

public class MesaDTO {
    private int id;
    private int numeroMesa;
    private String estado;
    private int capacidad;
    private int barId; 

    // Constructores
    public MesaDTO() {}

    public MesaDTO(int id, int numeroMesa, String estado, int capacidad, int barId) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.capacidad = capacidad;
        this.barId = barId;
    }

    public MesaDTO(int numeroMesa, String estado, int capacidad) {
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.capacidad = capacidad;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getBarId() {
        return barId;
    }

    public void setBarId(int barId) {
        this.barId = barId;
    }
}