package com.rdfj.mesero.mapper;

import com.rdfj.mesero.dto.ProductoDTO;
import com.rdfj.mesero.entity.Producto;

public class ProductoMapper {

    // Entidad -> DTO
    public static ProductoDTO productoToDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setCategoria(producto.getCategoria());

        // Relación con Bar
        if (producto.getBar() != null) {
            dto.setBar(BarMapper.barToDTO(producto.getBar()));
        }

        // Relación con Inventario
        if (producto.getInventario() != null) {
            dto.setInventarioDTO(InventarioMapper.inventarioToDTO(producto.getInventario()));
        }

        return dto;
    }

    // DTO -> Entidad
    public static Producto dtoToProducto(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoria(dto.getCategoria());

        // Relación con Bar
        if (dto.getBar() != null) {
            producto.setBar(BarMapper.dtoToBar(dto.getBar()));
        }

        // Relación con Inventario
        if (dto.getInventario() != null) {
            producto.setInventario(InventarioMapper.dtoToInventario(dto.getInventario()));
        }

        return producto;
    }
}