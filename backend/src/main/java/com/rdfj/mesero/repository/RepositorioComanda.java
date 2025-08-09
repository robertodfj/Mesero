package com.rdfj.mesero.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rdfj.mesero.entity.Comanda;

public interface RepositorioComanda extends JpaRepository<Comanda, Integer> {
    Optional<Comanda> findByFechaInicio(Date fechaInicio);
    List <Comanda> findByMesa_Id(Integer mesaID);
}
