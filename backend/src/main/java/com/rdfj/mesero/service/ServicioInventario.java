package com.rdfj.mesero.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Inventario;
import com.rdfj.mesero.entity.InventarioProducto;
import com.rdfj.mesero.entity.Usuario;
import com.rdfj.mesero.repository.RepositorioInventario;
import com.rdfj.mesero.repository.RepositorioUsuario;

@Service
public class ServicioInventario {

    @Autowired
    private RepositorioInventario repositorioInventario;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    // Obtener el inventario del bar del usuario autenticado
    public Inventario mostrarInventario() {
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario usuario = repositorioUsuario.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Bar bar = usuario.getBar();
        if (bar == null) {
            throw new RuntimeException("El usuario no está asignado a ningún bar");
        }

        return repositorioInventario.findByBar(bar)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }

    // Añadir un producto al inventario del bar del usuario autenticado
    public InventarioProducto añadirProducto(InventarioProducto productoNuevo) {
        Inventario inventario = mostrarInventario();

        productoNuevo.setInventario(inventario);
        inventario.getProductos().add(productoNuevo);

        repositorioInventario.save(inventario);
        return productoNuevo;
    }

    // Editar un producto del inventario automáticamente
    public InventarioProducto editarProducto(int productoId, int nuevaCantidad, String nuevaUnidad) {
        Inventario inventario = mostrarInventario();

        InventarioProducto producto = inventario.getProductos()
                .stream()
                .filter(p -> p.getProducto().getId() == productoId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en el inventario"));

        producto.setCantidad(nuevaCantidad);
        producto.setUnidadMedida(nuevaUnidad);

        repositorioInventario.save(inventario);
        return producto;
    }
}