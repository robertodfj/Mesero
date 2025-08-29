package com.rdfj.mesero.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rdfj.mesero.entity.Inventario;
import com.rdfj.mesero.entity.InventarioProducto;
import com.rdfj.mesero.entity.Producto;

@Repository
public interface RepositorioInventarioProducto extends JpaRepository<InventarioProducto, Integer> {

    // Buscar todos los productos de un inventario
    List<InventarioProducto> findByInventario(Inventario inventario);

    // Buscar un producto concreto dentro de un inventario
    Optional<InventarioProducto> findByInventarioAndProducto(Inventario inventario, Producto producto);

}