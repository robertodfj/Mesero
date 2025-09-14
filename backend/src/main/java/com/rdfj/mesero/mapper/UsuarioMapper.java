package com.rdfj.mesero.mapper;

import com.rdfj.mesero.dto.BarDTO;
import com.rdfj.mesero.dto.UsuarioDTO;
import com.rdfj.mesero.entity.Usuario;

public class UsuarioMapper {

    // Mapper completo (solo para uso independiente)
    public static UsuarioDTO usuarioToDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());

        // Solo mapear info básica del Bar para evitar ciclo
        if (usuario.getBar() != null) {
            BarDTO barDTO = new BarDTO();
            barDTO.setId(usuario.getBar().getId());
            barDTO.setNombre(usuario.getBar().getNombre());
            dto.setBar(barDTO);
        }

        return dto;
    }

    // Mapper simplificado para listas dentro de BarMapper
    public static UsuarioDTO usuarioToDTOSimple(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        // No se asigna bar completo para evitar ciclos
        return dto;
    }

    // Mappers inversos para DTO a entidad
    public static Usuario dtoToUsuario(UsuarioDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        // Asignar bar solo por id si se necesita
        return usuario;
    }

    public static Usuario dtoToUsuarioSimple(UsuarioDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        return usuario;
    }
}