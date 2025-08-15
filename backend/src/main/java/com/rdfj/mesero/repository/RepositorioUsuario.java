package com.rdfj.mesero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdfj.mesero.entity.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {
    boolean existsByEmail(String email);
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findById(Integer id);
}
