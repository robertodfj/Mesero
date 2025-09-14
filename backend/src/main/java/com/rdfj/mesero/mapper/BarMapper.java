package com.rdfj.mesero.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

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

        // Mesas
        List<MesaDTO> mesasDTO = Optional.ofNullable(bar.getMesas())
            .orElse(Collections.emptyList())
            .stream()
            .filter(Objects::nonNull)
            .map(MesaMapper::mesaToDTO)
            .collect(Collectors.toList());
        dto.setMesas(mesasDTO);

        // Usuarios
        List<UsuarioDTO> usuariosDTO = Optional.ofNullable(bar.getUsuarios())
            .orElse(Collections.emptyList())
            .stream()
            .filter(Objects::nonNull)
            .map(UsuarioMapper::usuarioToDTOSimple) // Solo info básica para evitar ciclos
            .collect(Collectors.toList());
        dto.setUsuarios(usuariosDTO);

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

        // Mesas
        List<Mesa> mesas = Optional.ofNullable(dto.getMesas())
            .orElse(Collections.emptyList())
            .stream()
            .filter(Objects::nonNull)
            .map(MesaMapper::dtoToMesa)
            .collect(Collectors.toList());
        bar.setMesas(mesas);

        // Usuarios
        List<Usuario> usuarios = Optional.ofNullable(dto.getUsuarios())
            .orElse(Collections.emptyList())
            .stream()
            .filter(Objects::nonNull)
            .map(UsuarioMapper::dtoToUsuarioSimple) // Solo info básica
            .collect(Collectors.toList());
        bar.setUsuarios(usuarios);

        return bar;
    }
}