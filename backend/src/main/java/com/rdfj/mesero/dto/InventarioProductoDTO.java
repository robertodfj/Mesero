package com.rdfj.mesero.dto;

public class InventarioProductoDTO {
    private int id;
    private int idproducto;
    private String nombreProducto;
    private int cantidad;
    private String unidadMedida;

    // Constructores

    public InventarioProductoDTO(){}

    public InventarioProductoDTO(int id, int idproducto, String nombreProducto, 
            int cantidad, String unidadMedida){
                this.id = id;
                this.idproducto = idproducto;
                this.nombreProducto = nombreProducto;
                this.cantidad = cantidad;
                this.unidadMedida = unidadMedida;
            }
    
    // Getters y setters
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getIdProducto(){
        return idproducto;
    }

    public void setIdProducto(int idproducto){
        this.idproducto = idproducto;
    }

    public String getNombre(){
        return nombreProducto;
    }

    public void setNombre(String nombreProducto){
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad(){
        return cantidad;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public String getUnidadMedida(){
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida){
        this.unidadMedida = unidadMedida;
    }
}
