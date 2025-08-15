package com.rdfj.mesero.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Usuario;
import com.rdfj.mesero.repository.RepositorioUsuario;

@Service
public class ServicioUsuario {
    
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    // Crear usuarios
    public Usuario añadirUsuario(Usuario usuario){
        return repositorioUsuario.save(usuario);
    }

    // Eliminar usuarios
    public void eliminarUsuario(String nombre) {
        Usuario usuario = repositorioUsuario.findByNombre(nombre)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        repositorioUsuario.delete(usuario);
    }

    // Mostrar todos 
    public List<Usuario> mostrarTodos(){
        return repositorioUsuario.findAll();
    }

    // Buscar por nombre
    public Usuario mostrarUsuarioNombre(String nombre){
         Usuario usuario = repositorioUsuario.findByNombre(nombre)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario;
    }

    // Buscar por email
    public Usuario mostrarUsuarioEmail(String email){
        Usuario usuario = repositorioUsuario.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario;
    }

    // Buscar por id
    public Usuario mostrarUsuarioId(Integer id){
        Usuario usuario = repositorioUsuario.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario;
    }
}
