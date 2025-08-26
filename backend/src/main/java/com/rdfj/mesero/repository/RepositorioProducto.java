package com.rdfj.mesero.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Producto;

public interface RepositorioProducto extends JpaRepository<Producto, Integer>{
    Producto findByNombreAndBarId(String nombre, Integer barId);
    boolean existsByNombreAndBarId(String nombre, Integer barId);
    List<Producto> findAllByBarId(Integer barId);
    Optional<Producto> findByIdAndBar(Integer id, Bar bar);
} 
