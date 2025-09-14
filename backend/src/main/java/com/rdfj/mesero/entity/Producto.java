package com.rdfj.mesero.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @DecimalMin(value = "0.01", message = "El precio de compra tiene que ser mayor de 0")
    private double precioCompraUD;

    @DecimalMin(value = "0.01", message = "El precio de venta tiene que ser mayor de 0")
    private double precioVentaUD;

    public enum Categoria {
        comida,
        bebida
    }

    @NotNull(message = "La categoría no puede estar vacía")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    // Relación con Bar (cada producto pertenece a un bar)
    @ManyToOne
    @JoinColumn(name = "bar_id", nullable = false)
    @JsonBackReference
    private Bar bar;

    // Relación con Inventario (cada producto pertenece a un inventario)
    @ManyToOne
    @JoinColumn(name = "inventario_id")
    private Inventario inventario;

    // Constructor vacío
    public Producto() {}

    // Constructor con todos los campos excepto id
    public Producto(String nombre, double precioCompraUD, double precioVentaUD, Categoria categoria, Bar bar, Inventario inventario) {
        this.nombre = nombre;
        this.precioCompraUD = precioCompraUD;
        this.precioVentaUD = precioVentaUD;
        this.categoria = categoria;
        this.bar = bar;
        this.inventario = inventario;
    }

    // Getters y setters
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


    public double getPrecioCompraUD() {
        return precioCompraUD;
    }

    public void setPrecioCompraUD(double precioCompraUD) {
        this.precioCompraUD = precioCompraUD;
    }

    public double getPrecioVentaUD() {
        return precioVentaUD;
    }

    public void setPrecioVentaUD(double precioVentaUD) {
        this.precioVentaUD = precioVentaUD;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    
}