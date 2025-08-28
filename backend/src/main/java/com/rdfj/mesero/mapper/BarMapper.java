package com.rdfj.mesero.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.rdfj.mesero.dto.BarDTO;
import com.rdfj.mesero.dto.MesaDTO;
import com.rdfj.mesero.dto.UsuarioDTO;
import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Mesa;
import com.rdfj.mesero.entity.Usuario;

public class BarMapper {

    public static BarDTO barToDTO(Bar bar) {
        if (bar == null) {
            return null;
        }

        BarDTO dto = new BarDTO();
        dto.setId(bar.getId());
        dto.setDireccion(bar.getDireccion());
        dto.setNombre(bar.getNombre());
        dto.setTelefono(bar.getTelefono());

        if (bar.getMesas() != null) {
            List<MesaDTO> mesasDTO = bar.getMesas()
                .stream()
                .map(MesaMapper::mesaToDTO)
                .collect(Collectors.toList());
            dto.setMesas(mesasDTO);
        }

        if (bar.getUsuarios() != null) {
            List<UsuarioDTO> usuariosDTO = bar.getUsuarios()
                .stream()
                .map(UsuarioMapper::usuarioToDTO)
                .collect(Collectors.toList());
            dto.setUsuarios(usuariosDTO);
        }

        return dto;
    }

    public static Bar dtoToBar(BarDTO dto) {
        if (dto == null) {
            return null;
        }

        Bar bar = new Bar();
        bar.setId(dto.getId());
        bar.setDireccion(dto.getDireccion());
        bar.setNombre(dto.getNombre());
        bar.setTelefono(dto.getTelefono());

        if (dto.getMesas() != null) {
            List<Mesa> mesas = dto.getMesas()
                .stream()
                .map(MesaMapper::dtoToMesa)
                .collect(Collectors.toList());
            bar.setMesas(mesas); // 🔹 ahora sí se asigna
        }

        if (dto.getUsuarios() != null) {
            List<Usuario> usuarios = dto.getUsuarios()
                .stream()
                .map(UsuarioMapper::dtoToUsuario)
                .collect(Collectors.toList());
            bar.setUsuarios(usuarios); // 🔹 ahora sí se asigna
        }

        return bar;
    }
}