package com.rdfj.mesero.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.rdfj.mesero.dto.ComandaDTO;
import com.rdfj.mesero.dto.DetalleComandaDTO;
import com.rdfj.mesero.entity.Comanda;
import com.rdfj.mesero.entity.DetalleComanda;

public class ComandaMapper {

    // De entidad a DTO
    public static ComandaDTO comandaToDTO(Comanda comanda){
        if (comanda == null) return null;

        ComandaDTO dto = new ComandaDTO();
        dto.setTotal(comanda.getTotal());
        dto.setEstado(comanda.getEstado());
        dto.setNumeroMesa(comanda.getMesa() != null ? comanda.getMesa().getNumeroMesa() : 0);

        if(comanda.getDetalles() != null) {
            List<DetalleComandaDTO> detallesDTO = comanda.getDetalles()
                .stream()
                .map(DetalleComandaMapper::detalleComandaToDTO)
                .collect(Collectors.toList());
            dto.setDetalles(detallesDTO);
        }

        return dto;
    }

    // De DTO a entidad (solo detalles, Mesa y Bar se asignan en el servicio)
    public static Comanda dtoToComanda(ComandaDTO comandaDTO){
        if (comandaDTO == null) return null;

        Comanda entity = new Comanda();
        entity.setTotal(comandaDTO.getTotal());
        entity.setEstado(comandaDTO.getEstado());

        if(comandaDTO.getDetalles() != null) {
            List<DetalleComanda> detallesEntity = comandaDTO.getDetalles()
                .stream()
                .map(DetalleComandaMapper::dtoToDetalleComanda)
                .collect(Collectors.toList());
            entity.setDetalles(detallesEntity);
        }

        return entity;
    }

}