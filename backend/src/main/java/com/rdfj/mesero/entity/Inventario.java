package com.rdfj.mesero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Min(value = 0, message = "La cantidad no puede ser negativa.")
    private int cantidad;

    @NotBlank(message = "La unidad de medida no puede estar vacia.")
    private String unidadMedida;

    @OneToOne
    @JoinColumn(name = "bar_id", nullable = false)
    private Bar bar;

    // Constructores

    public Inventario() {
    }

    public Inventario(int id, Producto producto, int cantidad, String unidadMedida, Bar bar) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.bar = bar;
    }

    // Getters y setters
    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
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

    public Bar getBar() { 
        return bar; 
    }

    public void setBar(Bar bar) { 
        this.bar = bar; 
    }
}
