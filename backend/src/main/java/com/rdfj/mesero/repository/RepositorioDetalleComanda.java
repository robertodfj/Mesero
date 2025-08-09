package com.rdfj.mesero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdfj.mesero.entity.DetalleComanda;

public interface RepositorioDetalleComanda extends JpaRepository<DetalleComanda, Integer>{
    Optional<DetalleComanda> findByComanda_Id(Integer id);
} 
