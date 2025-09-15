package com.rdfj.mesero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdfj.mesero.entity.Mesa;

public interface RepositorioMesa extends JpaRepository<Mesa, Integer> {
    Optional<Mesa> findByNumeroMesa(Integer numeroMesa);
    boolean existsByNumeroMesa(Integer numeroMesa);
    void deleteByNumeroMesa(Integer numeroMesa);
    Optional<Mesa> findByNumeroMesaAndBarId(int numeroMesa, int barId);
}
