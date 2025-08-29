package com.rdfj.mesero.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Inventario;
import com.rdfj.mesero.repository.RepositorioBar;
import com.rdfj.mesero.repository.RepositorioInventario;

@Service
public class ServicioBar {
    
    @Autowired
    private RepositorioBar repositorioBar;

    @Autowired
    private RepositorioInventario repositorioInventario;


    // Crear Bar
    public Bar añadirBar(Bar bar){
        
        Bar barguardado = repositorioBar.save(bar);
        // Crear un Inventario para el bar automaticamente.
        Inventario inventario = new Inventario();
        inventario.setBar(bar);
        inventario = repositorioInventario.save(inventario);

        return barguardado;
    }

    // Eliminar Bar
    public void eliminar(String nombre){
        Bar bar = repositorioBar.findByNombre(nombre)
            .orElseThrow(() -> new RuntimeException("El bar no existe"));
        repositorioBar.delete(bar);
    }

    // Buscar id
    public Optional<Bar> buscarID(Integer id){
        return repositorioBar.findById(id);
    }

    // Buscar por nombre
    public Optional<Bar> buscarNombre(String nombre){
        return repositorioBar.findByNombre(nombre);
    }

    // Editar Bar
    public Bar actualizarBar(Integer id, Bar barActualizado) {
        Bar bar = repositorioBar.findById(id)
            .orElseThrow(() -> new RuntimeException("Bar no encontrado"));
        
        bar.setNombre(barActualizado.getNombre());
        bar.setDireccion(barActualizado.getDireccion());
        bar.setTelefono(barActualizado.getTelefono());
        return repositorioBar.save(bar);
    }
}
