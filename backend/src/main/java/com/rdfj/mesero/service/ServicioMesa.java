package com.rdfj.mesero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Mesa;
import com.rdfj.mesero.repository.RepositorioMesa;

@Service
public class ServicioMesa {
    
    @Autowired
    private RepositorioMesa repositorioMesa;

    // Añadir mesa
    public Mesa añadirMesa(Mesa mesa){
        if (existsByNumeroMesa(mesa.getNumeroMesa())) {
            throw new RuntimeException("Ya existe una mesa con este número");
        }
        return repositorioMesa.save(mesa);
    }

    // Eliminar mesa
    public void eliminarMesa(Integer id){
        repositorioMesa.deleteById(id);
    }

    // Ver todas las mesas
    public List<Mesa> verMesas(){
        return repositorioMesa.findAll();
    }

    // Comprobar existencia
    public boolean existsByNumeroMesa(Integer numeroMesa){
        return repositorioMesa.existsByNumeroMesa(numeroMesa);
    }

}
