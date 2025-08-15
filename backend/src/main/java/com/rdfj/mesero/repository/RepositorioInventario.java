package com.rdfj.mesero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Inventario;

public interface RepositorioInventario extends JpaRepository<Inventario, Integer> {
    List<Inventario> findByBar(Bar bar);
} 
