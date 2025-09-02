package com.rdfj.mesero.dto;

public class ActualizarStockDTO {
    private Integer productoId;
    private String nuevaUnidad;
    private Integer nuevaCantidad;

    // Getters y setters
    public Integer getProductoId() {
        return productoId;
    }
    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }
    public String getNuevaUnidad() {
        return nuevaUnidad;
    }
    public void setNuevaUnidad(String nuevaUnidad) {
        this.nuevaUnidad = nuevaUnidad;
    }
    public Integer getNuevaCantidad() {
        return nuevaCantidad;
    }
    public void setNuevaCantidad(Integer nuevaCantidad) {
        this.nuevaCantidad = nuevaCantidad;
    }
}
