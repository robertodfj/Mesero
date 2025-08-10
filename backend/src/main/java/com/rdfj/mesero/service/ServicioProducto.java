package com.rdfj.mesero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Producto;
import com.rdfj.mesero.repository.RepositorioProducto;

@Service
public class ServicioProducto {
    
    @Autowired
    private RepositorioProducto repositorioProducto;

    // Nuevo Producto
    public Producto añadirProducto(Producto producto){
        return repositorioProducto.save(producto);
    }

    // Eliminar producto
    public void eliminarProducto(Integer id){
        repositorioProducto.deleteById(id);
    }

    // Vet todos los productos
    public List<Producto> todosProducto(){
        return repositorioProducto.findAll();
    }

    // Ver existencia de producto
    public boolean exiteProducto(String nombre){
        return repositorioProducto.existsByNombre(nombre);
    }

}
