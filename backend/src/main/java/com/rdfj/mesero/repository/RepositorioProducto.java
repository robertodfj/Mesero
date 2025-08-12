package com.rdfj.mesero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdfj.mesero.entity.Producto;

public interface RepositorioProducto extends JpaRepository<Producto, Integer>{
    Producto findByNombre(String nombre);
    boolean existsByNombre(String nombre);
    List<Producto> findAll();
} 
