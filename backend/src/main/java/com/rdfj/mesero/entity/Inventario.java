package com.rdfj.mesero.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "bar_id", nullable = false)
    private Bar bar;

    @OneToMany(mappedBy = "inventario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventarioProducto> productos = new ArrayList<>();

    // Constructor 
    public Inventario() {}

    public Inventario(Bar bar, List<InventarioProducto> productos) {
        this.bar = bar;
        this.productos = productos;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public List<InventarioProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<InventarioProducto> productos) {
        this.productos = productos;
    }
}