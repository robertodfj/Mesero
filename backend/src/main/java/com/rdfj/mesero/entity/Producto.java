package com.rdfj.mesero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @DecimalMin(value = "0.01", message = "El precio tiene que ser mayor de 0")
    private double precio;

    @DecimalMin(value = "0.01", message = "El precio de compra tiene que ser mayor de 0")
    private double precioCompraUD;

    @DecimalMin(value = "0.01", message = "El precio de venta tiene que ser mayor de 0")
    private double precioVentaUD;

    public enum Categoria {
        comida,
        bebida
    }

    @NotNull(message = "La categoria no puede estar vacia")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    // Constructor 
    public Producto() {
    }

    public Producto(String nombre, double precio, double precioCompraUD, double precioVentaUD, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.precioCompraUD = precioCompraUD;
        this.precioVentaUD = precioVentaUD;
        this.categoria = categoria;
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

    public double getPrecio() { 
        return precio; 
    }

    public void setPrecio(double precio) { 
        this.precio = precio; 
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
}