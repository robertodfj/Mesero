package com.rdfj.mesero.mapper;

import com.rdfj.mesero.dto.DetalleComandaDTO;
import com.rdfj.mesero.dto.ProductoDTO;
import com.rdfj.mesero.entity.DetalleComanda;
import com.rdfj.mesero.entity.Producto;

public class DetalleComandaMapper {

    public static DetalleComandaDTO detalleComandaToDTO(DetalleComanda entity) {
        if (entity == null) {
            return null;
        }
        DetalleComandaDTO dto = new DetalleComandaDTO();
        dto.setId(entity.getId());
        dto.setComandaId(entity.getComanda().getId());
        dto.setCantidad(entity.getCantidad());
        dto.setObservaciones(entity.getObservaciones());
        dto.setPrecio(entity.getPrecio());
        dto.setProducto(productoToDTO(entity.getProducto()));
        return dto;
    }

    public static DetalleComanda dtoToDetalleComanda(DetalleComandaDTO dto) {
        if (dto == null) {
            return null;
        }
        DetalleComanda entity = new DetalleComanda();
        entity.setId(dto.getId());
        // Aquí deberías asignar la Comanda usando solo el ID o un método para obtenerla
        // entity.setComanda(algún método para obtener Comanda por dto.getComandaId());
        entity.setCantidad(dto.getCantidad());
        entity.setObservaciones(dto.getObservaciones());
        entity.setPrecio(dto.getPrecio());
        entity.setProducto(productoDTOToProducto(dto.getProducto()));
        return entity;
    }

    // Mappers para Producto (puedes tenerlos en ProductoMapper si prefieres)
    public static ProductoDTO productoToDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setCategoria(producto.getCategoria());
        return dto;
    }

    public static Producto productoDTOToProducto(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }
}