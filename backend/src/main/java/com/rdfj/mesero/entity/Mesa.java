package com.rdfj.mesero.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 1, message = "El numero de mesa debe ser mayor o igual a 1")
    @Column(unique = true)
    private int numeroMesa;

    public enum Estado{
        Libre,
        Ocupada,
        Reservada
    };

    @NotNull(message = "El estado no puede estar vacio")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Min(value = 1, message = "El numero de mesa debe ser mayor o igual a 1")
    private int capacidad;

    // Getters y setters
    public int getId() {
        return id;
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
