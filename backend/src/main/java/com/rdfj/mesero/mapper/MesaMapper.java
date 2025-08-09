package com.rdfj.mesero.mapper;

import com.rdfj.mesero.dto.MesaDTO;
import com.rdfj.mesero.entity.Mesa;

public class MesaMapper {
    public static MesaDTO mesaToDTO(Mesa mesa){
        if (mesa == null) {
            return null;
        }
        MesaDTO dto = new MesaDTO();
        dto.setId(mesa.getId());
        dto.setNumeroMesa(mesa.getNumeroMesa());
        dto.setEstado(MesaDTO.Estado.valueOf(mesa.getEstado().name()));
        dto.setCapacidad(mesa.getCapacidad());

        return dto;
    };

    public static Mesa dtoToMesa(MesaDTO mesaDTO){
        if (mesaDTO == null) {
            return null;
        }
        Mesa entity = new Mesa();
        entity.setId(mesaDTO.getId());
        entity.setNumeroMesa(mesaDTO.getNumeroMesa());
        entity.setEstado(Mesa.Estado.valueOf(mesaDTO.getEstado().name()));
        entity.setCapacidad(mesaDTO.getCapacidad());

        return entity;

    }
}
