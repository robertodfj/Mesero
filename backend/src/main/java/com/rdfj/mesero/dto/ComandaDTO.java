package com.rdfj.mesero.dto;

import java.sql.Date;
import java.util.List;

import com.rdfj.mesero.entity.Comanda.Estado;;

public class ComandaDTO {
    private int id;

    private MesaDTO mesa;

    private Date fechaInicio;

    private Date fechaFin;

    private Estado estado;

    private List<DetalleComandaDTO> detalles;

    private double total;

    // Constructor

    public ComandaDTO() {

    }

    public ComandaDTO(MesaDTO mesa, Date fechaInicio, Estado estado, double total) {
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

    public MesaDTO getMesa() {
        return mesa;
    }

    public void setMesa(MesaDTO mesa) {
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

    public List<DetalleComandaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleComandaDTO> detalles) {
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
                            .mapToDouble(DetalleComandaDTO::getSubtotal)
                            .sum();
    }
}
