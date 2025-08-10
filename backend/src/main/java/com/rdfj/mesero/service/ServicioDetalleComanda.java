package com.rdfj.mesero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.DetalleComanda;
import com.rdfj.mesero.repository.RepositorioDetalleComanda;

@Service
public class ServicioDetalleComanda {
    @Autowired
    private RepositorioDetalleComanda repositorioDetalleComanda;

    // Añadir detalle comanda
    public DetalleComanda añadirDetalleComanda(DetalleComanda detalleComanda){
        return repositorioDetalleComanda.save(detalleComanda);
    }

    // Borrar detallecomanda        
    public void borrarDetalleComanda(Integer id){
        repositorioDetalleComanda.deleteById(id);
    }
}
