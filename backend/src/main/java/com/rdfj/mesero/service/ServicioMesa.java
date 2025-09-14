package com.rdfj.mesero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Mesa;
import com.rdfj.mesero.repository.RepositorioBar;
import com.rdfj.mesero.repository.RepositorioMesa;

@Service
public class ServicioMesa {
    
    @Autowired
    private RepositorioMesa repositorioMesa;

    @Autowired
    private RepositorioBar repositorioBar;

    // Añadir mesa
    public Mesa añadirMesa(Mesa mesa){
        if (existsByNumeroMesa(mesa.getNumeroMesa())) {
            throw new RuntimeException("Ya existe una mesa con este número");
        }
        int barid = mesa.getBar().getId();
        Bar bar = repositorioBar.findById(barid)
                        .orElseThrow(() -> new RuntimeException("Bar no existe"));
        mesa.setBar(bar);
        return repositorioMesa.save(mesa);
    }

    // Eliminar mesa
    public void eliminarPorNumeroMesa(int numMesa) {
    Mesa mesa = repositorioMesa.findByNumeroMesa(numMesa)
                    .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        repositorioMesa.delete(mesa);
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
