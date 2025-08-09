package com.rdfj.mesero.mapper;

import com.rdfj.mesero.dto.ProductoDTO;
import com.rdfj.mesero.entity.Producto;

public class ProductoMapper {

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