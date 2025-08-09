package com.rdfj.mesero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

@Entity
public class DetalleComanda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_comanda", nullable = false)
    private Comanda comanda;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @Min(value = 1, message = "La cantidad minima es 1")
    private int cantidad;

    private String observaciones;

    @DecimalMin(value = "0.01", message = "El precio tiene que ser mayor de 0")
    private double precio;

    // Constructor

    public DetalleComanda() {}

    public DetalleComanda(Comanda comanda, Producto producto, String observaciones, double precio, int cantidad) {
        this.comanda = comanda;
        this.producto = producto;
        this.observaciones = observaciones;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Calcular el precio del detalleComanda
    public double getSubtotal(){
        return this.precio * this.cantidad;
    }

}
