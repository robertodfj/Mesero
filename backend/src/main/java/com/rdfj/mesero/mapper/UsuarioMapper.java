package com.rdfj.mesero.mapper;

import com.rdfj.mesero.dto.UsuarioDTO;
import com.rdfj.mesero.entity.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO usuarioToDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());

        if (usuario.getBar() != null) {
            dto.setBar(BarMapper.barToDTO(usuario.getBar()));
        }

        return dto;
    }

    public static Usuario dtoToUsuario(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());

        if (dto.getBar() != null) {
            usuario.setBar(BarMapper.dtoToBar(dto.getBar()));
        }

        return usuario;
    }
}