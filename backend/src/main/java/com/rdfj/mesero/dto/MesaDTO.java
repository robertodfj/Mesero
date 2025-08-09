package com.rdfj.mesero.dto;


public class MesaDTO {
    private int id;

    private int numeroMesa;

    public enum Estado{
        Libre,
        Ocupada,
        Reservada
    };
    
    private Estado estado;

    private int capacidad;

    // Constructor

    public MesaDTO(){

    }

    public MesaDTO(int numeroMesa, Estado estado, int capacidad){
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.capacidad = capacidad;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }


}
