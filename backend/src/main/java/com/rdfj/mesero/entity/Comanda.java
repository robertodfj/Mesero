package com.rdfj.mesero.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="bar_id", nullable=false)
    private Bar bar;

    @ManyToOne
    @JoinColumn(name= "mesa_id", nullable = false)
    private Mesa mesa;

    @NotNull(message = "La fecha de inicio no puede estar vacia")
    private Date fechaInicio;

    @NotNull(message = "La decha de finalizacion no puede estar vacia")
    private Date fechaFin;

    public enum Estado{
        abierta,
        cerrada,
        cancelada
    }

    @NotNull(message = "El estado no puede estar vacio")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleComanda> detalles;

    private double total;

    // Constructor

    public Comanda() {

    }

    public Comanda(Mesa mesa, Date fechaInicio, Estado estado, double total) {
        this.mesa = mesa;
        this.fechaInicio = fechaInicio;
        this.estado = estado;
        this.total = total;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<DetalleComanda> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleComanda> detalles) {
        this.detalles = detalles;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double calcularTotal(){
        if (detalles == null || detalles.isEmpty()) {
            return 0.0;
        } return detalles.stream()
                            .mapToDouble(DetalleComanda::getSubtotal)
                            .sum();
    }
}
