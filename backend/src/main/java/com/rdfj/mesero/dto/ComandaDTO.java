package com.rdfj.mesero.dto;

import java.util.List;
import com.rdfj.mesero.entity.Comanda.Estado;

public class ComandaDTO {
    
    private int id;
    private int numeroMesa;       
    private List<DetalleComandaDTO> detalles;
    private Estado estado;
    private double total;

    // Constructor 
    public ComandaDTO() {}

    public ComandaDTO(int numeroMesa, Estado estado, double total, List<DetalleComandaDTO> detalles) {
        this.numeroMesa = numeroMesa;
        this.estado = estado;
        this.total = total;
        this.detalles = detalles;
    }

    // Getters y setters
    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public List<DetalleComandaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleComandaDTO> detalles) {
        this.detalles = detalles;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Método para calcular total desde los detalles
    public double calcularTotal() {
        if (detalles == null || detalles.isEmpty()) return 0.0;
        return detalles.stream()
                       .mapToDouble(DetalleComandaDTO::getSubtotal)
                       .sum();
    }
}