package com.rdfj.mesero.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class InventarioProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "inventario_id", nullable = false)
    @JsonBackReference
    private Inventario inventario;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonManagedReference
    private Producto producto;

    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private int cantidad;

    @NotBlank(message = "La unidad de medida no puede estar vacía")
    private String unidadMedida;

    // Constructor 
    public InventarioProducto() {}

    public InventarioProducto(Inventario inventario, Producto producto, int cantidad, String unidadMedida) {
        this.inventario = inventario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}