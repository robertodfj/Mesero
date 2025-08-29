package com.rdfj.mesero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.DetalleComanda;
import com.rdfj.mesero.entity.InventarioProducto;
import com.rdfj.mesero.repository.RepositorioDetalleComanda;
import com.rdfj.mesero.repository.RepositorioInventarioProducto;

@Service
public class ServicioDetalleComanda {
    
    @Autowired
    private RepositorioDetalleComanda repositorioDetalleComanda;

    @Autowired
    private RepositorioInventarioProducto repositorioInventarioProducto;

    // Añadir detalle comanda
    public DetalleComanda añadirDetalleComanda(DetalleComanda detalleComanda){
        DetalleComanda nuevoDetalleComanda = repositorioDetalleComanda.save(detalleComanda);

        // Restar inventario del producto
        InventarioProducto inventarioProducto = repositorioInventarioProducto
                .findByInventarioAndProducto(detalleComanda.getComanda().getBar().getInventario(), 
                detalleComanda.getProducto())
                .orElseThrow(()-> new RuntimeException("No se encontro el producto"));

        int nuevaCantidad = inventarioProducto.getCantidad() - detalleComanda.getCantidad();
        if (nuevaCantidad<0) {
            throw new RuntimeException("No hay suficiente cantidad en el inventario");
        }
        inventarioProducto.setCantidad(nuevaCantidad);
        repositorioInventarioProducto.save(inventarioProducto);

        return nuevoDetalleComanda;
    }

    // Borrar detallecomanda        
    public void borrarDetalleComanda(Integer id){
        repositorioDetalleComanda.deleteById(id);
    }
}
