package com.rdfj.mesero.dto;

import com.rdfj.mesero.entity.Producto.Categoria;

public class ProductoDTO {

    private int id;

    private String nombre;

    private double precio;

    private Categoria categoria;

    private BarDTO bar;

    private InventarioDTO inventarioDTO;

    public ProductoDTO() {}

    public ProductoDTO(int id, String nombre, double precio, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Getters y setters básicos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BarDTO getBar() {
        return bar;
    }

    public void setBar(BarDTO bar) {
        this.bar = bar;
    }

    public InventarioDTO getInventarioDTO() {
        return inventarioDTO;
    }

    public void setInventarioDTO(InventarioDTO inventarioDTO) {
        this.inventarioDTO = inventarioDTO;
    }
}