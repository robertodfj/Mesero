package com.rdfj.mesero.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.rdfj.mesero.dto.InventarioDTO;
import com.rdfj.mesero.dto.ProductoDTO;
import com.rdfj.mesero.entity.Inventario;

public class InventarioMapper {

    public static InventarioDTO inventarioToDTO(Inventario inventario) {
        if (inventario == null) {
            return null;
        }

        InventarioDTO dto = new InventarioDTO();
        dto.setId(inventario.getId());

        // 🔹 Mapeo de Bar -> BarDTO
        if (inventario.getBar() != null) {
            dto.setBar(BarMapper.barToDTO(inventario.getBar()));
        }

        // 🔹 Mapeo de Productos -> ProductoDTO
        if (inventario.getProductos() != null) {
            List<ProductoDTO> productosDTO = inventario.getProductos()
                    .stream()
                    .map(ProductoMapper::productoToDTO)
                    .collect(Collectors.toList());
            dto.setProductos(productosDTO);
        }

        return dto;
    }

    public static Inventario dtoToInventario(InventarioDTO dto) {
        if (dto == null) {
            return null;
        }

        Inventario inventario = new Inventario();
        inventario.setId(dto.getId());

        // 🔹 Mapeo inverso BarDTO -> Bar
        if (dto.getBar() != null) {
            inventario.setBar(BarMapper.dtoToBar(dto.getBar()));
        }

        // 🔹 Mapeo inverso ProductoDTO -> Producto
        if (dto.getProductos() != null) {
            inventario.setProductos(dto.getProductos()
                    .stream()
                    .map(ProductoMapper::dtoToProducto)
                    .collect(Collectors.toList()));
        }

        return inventario;
    }
}