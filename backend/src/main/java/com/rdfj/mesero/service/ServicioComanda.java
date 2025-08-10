package com.rdfj.mesero.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Comanda;
import com.rdfj.mesero.repository.RepositorioComanda;

@Service
public class ServicioComanda {
    
    @Autowired
    private RepositorioComanda repositorioComanda;

    // Añadir comanda
    public Comanda añadirComanda(Comanda comanda){
        return repositorioComanda.save(comanda);
    }

    // Eliminar Comanda
    public void eliminarComanda(Integer id){
        repositorioComanda.deleteById(id);
    }

    // Mostrar todas las comandas
    public List<Comanda> verComandas(){
        return repositorioComanda.findAll();
    }

    // Mostrar las comandas de fecha inicio
    public Optional<Comanda> buscarComanda(Date fecha){
        return repositorioComanda.findByFechaInicio(fecha);
    }
}
