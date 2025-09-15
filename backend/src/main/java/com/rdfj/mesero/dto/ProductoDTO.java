package com.rdfj.mesero.dto;

import com.rdfj.mesero.entity.Producto.Categoria;

public class ProductoDTO {

    private int id;

    private String nombre;

    private double precioCompra;

    private double precioVenta;


    private Categoria categoria;

    private BarDTO bar;

    private InventarioDTO inventario;

    public ProductoDTO() {}

    public ProductoDTO(int id, String nombre, double precioCompra, double precioVenta, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
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

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
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

    public InventarioDTO getInventario() {
        return inventario;
    }

    public void setInventarioDTO(InventarioDTO inventario) {
        this.inventario = inventario;
    }
}