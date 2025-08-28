package com.rdfj.mesero.dto;

public class DetalleComandaDTO {

    private int id;
    private int comandaId;
    private ProductoDTO producto;
    private int cantidad;
    private String observaciones;
    private double precio;

    // Constructor 
    public DetalleComandaDTO() {}

    public DetalleComandaDTO(int comandaId, ProductoDTO producto, String observaciones, double precio, int cantidad) {
        this.comandaId = comandaId;
        this.producto = producto;
        this.observaciones = observaciones;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComandaId() {
        return comandaId;
    }

    public void setComandaId(int comandaId) {
        this.comandaId = comandaId;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    // Método para calcular subtotal
    public double getSubtotal() {
        return this.precio * this.cantidad;
    }
}