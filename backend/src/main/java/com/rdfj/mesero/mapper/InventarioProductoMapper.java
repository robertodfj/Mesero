package com.rdfj.mesero.mapper;

import com.rdfj.mesero.dto.InventarioProductoDTO;
import com.rdfj.mesero.entity.InventarioProducto;
import com.rdfj.mesero.entity.Producto;

public class InventarioProductoMapper {
    public static InventarioProductoDTO toDTO(InventarioProducto inventarioProducto){
        if (inventarioProducto == null) {
            return null;
        }

        InventarioProductoDTO dto = new InventarioProductoDTO();
        dto.setId(inventarioProducto.getId());
        dto.setCantidad(inventarioProducto.getCantidad());
        dto.setUnidadMedida(inventarioProducto.getUnidadMedida());

        if (inventarioProducto.getProducto() != null) {
            dto.setIdProducto(inventarioProducto.getProducto().getId());
            dto.setNombreProducto(inventarioProducto.getProducto().getNombre());
        }
        return dto;
    }

    public static InventarioProducto toEntity(InventarioProductoDTO dto){
        if (dto == null) {
            return null;
        }

        InventarioProducto entity = new InventarioProducto();
        entity.setId(dto.getId());
        entity.setCantidad(dto.getCantidad());
        entity.setUnidadMedida(dto.getUnidadMedida());

        if (dto.getIdProducto()>0) {
            Producto producto = new Producto();
            entity.setProducto(producto);
        }

        return entity;
    }
}
