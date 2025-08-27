package com.rdfj.mesero.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.dto.RegistrarUsuarioDTO;
import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Usuario;
import com.rdfj.mesero.repository.RepositorioBar;
import com.rdfj.mesero.repository.RepositorioUsuario;

@Service
public class ServicioUsuario {
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired 
    private RepositorioBar repositorioBar;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Registrar usuario (Usamos el dto)
    public Usuario registrarUsuario(RegistrarUsuarioDTO dto){
        Bar bar = repositorioBar.findById(dto.getBarId())
            .orElseThrow(() -> new RuntimeException("Bar no encontrado"));
        

        if(repositorioUsuario.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setEmail(dto.getEmail());
        usuario.setBar(bar);
        usuario.setRol(dto.getRol());

        return repositorioUsuario.save(usuario);
    }

    // Eliminar usuario por email
    public void eliminarUsuario(String email){
        Usuario usuario = repositorioUsuario.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        repositorioUsuario.delete(usuario);
    }

    // Mostrar todos
    public List<Usuario> mostrarTodos(){
        return repositorioUsuario.findAll();
    }

    // Buscar por email
    public Usuario buscarPorEmail(String email){
        return repositorioUsuario.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Buscar por nombre
    public Usuario buscarPorNombre(String nombre){
        return repositorioUsuario.findByNombre(nombre)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
