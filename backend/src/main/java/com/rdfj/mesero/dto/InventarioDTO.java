package com.rdfj.mesero.dto;

import java.util.ArrayList;
import java.util.List;

import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.InventarioProducto;

public class InventarioDTO {
    private int id;
    private BarDTO bar;
    private List<InventarioProductoDTO> productos = new ArrayList<>();

    // Constructor 
    public InventarioDTO() {}

    public InventarioDTO(BarDTO bar, List<InventarioProductoDTO> productos) {
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

    public BarDTO getBar() {
        return bar;
    }

    public void setBar(BarDTO bar) {
        this.bar = bar;
    }

    public List<InventarioProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<InventarioProductoDTO> productos) {
        this.productos = productos;
    }
}
