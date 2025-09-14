package com.rdfj.mesero.mapper;

import com.rdfj.mesero.dto.MesaDTO;
import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Mesa;

public class MesaMapper {

    // Convierte Mesa → MesaDTO
    public static MesaDTO mesaToDTO(Mesa mesa){
        if (mesa == null) {
            return null;
        }

        MesaDTO dto = new MesaDTO();
        dto.setId(mesa.getId());
        dto.setNumeroMesa(mesa.getNumeroMesa());
        dto.setCapacidad(mesa.getCapacidad());
        dto.setEstado(mesa.getEstado().name()); // Enum → String
        dto.setBarId(mesa.getBar() != null ? mesa.getBar().getId() : null);

        return dto;
    }

    // Convierte MesaDTO → Mesa
    public static Mesa dtoToMesa(MesaDTO dto){
        if (dto == null) {
            return null;
        }

        Mesa mesa = new Mesa();
        mesa.setId(dto.getId());
        mesa.setNumeroMesa(dto.getNumeroMesa());
        mesa.setCapacidad(dto.getCapacidad());

        // Validar y convertir String → Enum
        if (dto.getEstado() == null) {
            throw new RuntimeException("Estado de la mesa no puede ser null");
        }

        try {
            mesa.setEstado(Mesa.Estado.valueOf(dto.getEstado().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(
                "Estado inválido. Valores permitidos: LIBRE, OCUPADA, RESERVADA"
            );
        }

        // Asignar Bar temporalmente solo con el ID
        if (dto.getBarId() != 0) {
            Bar bar = new Bar();
            bar.setId(dto.getBarId());
            mesa.setBar(bar);
        }

        return mesa;
    }
}