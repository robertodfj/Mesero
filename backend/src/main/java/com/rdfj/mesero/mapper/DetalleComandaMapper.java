package com.rdfj.mesero.mapper;

import com.rdfj.mesero.dto.DetalleComandaDTO;
import com.rdfj.mesero.entity.DetalleComanda;

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
        dto.setProducto(ProductoMapper.productoToDTO(entity.getProducto()));
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
        entity.setProducto(ProductoMapper.dtoToProducto(dto.getProducto()));
        return entity;
    }
}