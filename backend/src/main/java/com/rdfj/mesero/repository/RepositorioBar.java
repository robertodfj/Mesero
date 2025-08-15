package com.rdfj.mesero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdfj.mesero.entity.Bar;

public interface RepositorioBar extends JpaRepository<Bar, Integer>{
    Optional<Bar> findByNombre(String nombre);
    Optional<Bar> findById(Integer id);
} 