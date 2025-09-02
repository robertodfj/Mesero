package com.rdfj.mesero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Inventario;
import com.rdfj.mesero.entity.InventarioProducto;
import com.rdfj.mesero.entity.Producto;
import com.rdfj.mesero.entity.Usuario;
import com.rdfj.mesero.repository.RepositorioInventario;
import com.rdfj.mesero.repository.RepositorioInventarioProducto;
import com.rdfj.mesero.repository.RepositorioProducto;
import com.rdfj.mesero.repository.RepositorioUsuario;

@Service
public class ServicioInventario {

    @Autowired
    private RepositorioInventario repositorioInventario;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioProducto repositorioProducto;

    @Autowired
    private RepositorioInventarioProducto inventarioProductoRepository;

    public Bar getBar(){
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = repositorioUsuario.findByEmail(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.getBar();
    }

    // Obtener el inventario del bar del usuario autenticado
    public Inventario mostrarInventario() {
        Bar bar = getBar();
        if (bar == null) {
            throw new RuntimeException("El usuario no está asignado a ningún bar");
        }

        return repositorioInventario.findByBar(bar)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));
    }

    // Editar el inventario (actualizar stock)
    public InventarioProducto actualizarStock(
            Integer productoId,
            String nuevaUnidad,
            Integer nuevaCantidad) {

        Bar bar = getBar();

        // Recuperamos inventario del bar
        Inventario inventario = repositorioInventario.findByBar(bar)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Inventario no encontrado"));

        // Verificamos que el producto existe en ese bar
        Producto producto = repositorioProducto.findByIdAndBar(productoId, bar)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Buscamos el producto dentro del inventario
        InventarioProducto invProd = inventarioProductoRepository
                .findByInventarioAndProducto(inventario, producto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado en inventario"));

        // Actualizamos
        if (nuevaUnidad != null) invProd.setUnidadMedida(nuevaUnidad);
        if (nuevaCantidad != null) invProd.setCantidad(nuevaCantidad);

        return inventarioProductoRepository.save(invProd);
    }
}