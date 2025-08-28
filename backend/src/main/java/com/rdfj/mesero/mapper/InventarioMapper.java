package com.rdfj.mesero.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.rdfj.mesero.dto.InventarioDTO;
import com.rdfj.mesero.dto.InventarioProductoDTO;
import com.rdfj.mesero.entity.Inventario;

public class InventarioMapper {

    public static InventarioDTO inventarioToDTO(Inventario inventario) {
        if (inventario == null) return null;

        InventarioDTO dto = new InventarioDTO();
        dto.setId(inventario.getId());

        if (inventario.getBar() != null) {
            dto.setBar(BarMapper.barToDTO(inventario.getBar()));
        }

        if (inventario.getProductos() != null) {
            List<InventarioProductoDTO> productosDTO = inventario.getProductos()
                    .stream()
                    .map(InventarioProductoMapper::toDTO)
                    .collect(Collectors.toList());
            dto.setProductos(productosDTO);
        }

        return dto;
    }

    public static Inventario dtoToInventario(InventarioDTO dto) {
        if (dto == null) return null;

        Inventario inventario = new Inventario();
        inventario.setId(dto.getId());

        if (dto.getBar() != null) {
            inventario.setBar(BarMapper.dtoToBar(dto.getBar()));
        }

        if (dto.getProductos() != null) {
            inventario.setProductos(dto.getProductos()
                    .stream()
                    .map(InventarioProductoMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        return inventario;
    }
}