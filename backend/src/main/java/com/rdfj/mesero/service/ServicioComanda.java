package com.rdfj.mesero.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Comanda;
import com.rdfj.mesero.entity.DetalleComanda;
import com.rdfj.mesero.repository.RepositorioComanda;

@Service
public class ServicioComanda {
    
    @Autowired
    private RepositorioComanda repositorioComanda;

    @Autowired
    private ServicioDetalleComanda servicioDetalleComanda;

    // Añadir comanda nueva
    public Comanda añadirComanda(Comanda comanda){
        comanda.setTotal(0.0); // inicializamos total en 0
        return repositorioComanda.save(comanda);
    }

    // Añadir detalle a una comanda existente
    public Comanda añadirDetalle(Integer idComanda, DetalleComanda detalleComanda){
        Comanda comanda = repositorioComanda.findById(idComanda)
                .orElseThrow(() -> new RuntimeException("Comanda no encontrada"));

        // Vinculamos el detalle comanda a la comanda
        detalleComanda.setComanda(comanda);

        // Guardamos detalle y actualizamos inventario automáticamente
        servicioDetalleComanda.añadirDetalleComanda(detalleComanda);

        // Recalculamos total de la comanda
        comanda.setTotal(comanda.calcularTotal());

        return repositorioComanda.save(comanda);
    }

    // Cerrar una comanda
    public Comanda cerrarComanda(Integer id) {
        Comanda comanda = repositorioComanda.findById(id)
                .orElseThrow(() -> new RuntimeException("Comanda no encontrada"));

        if (comanda.getEstado() == Comanda.Estado.cerrada) {
            throw new RuntimeException("La comanda ya está cerrada");
        }

        if (comanda.getEstado() == Comanda.Estado.cancelada) {
            throw new RuntimeException("No se puede cerrar una comanda cancelada");
        }

        // recalculamos el total
        comanda.setTotal(comanda.calcularTotal());
        // marcamos fecha de fin
        comanda.setFechaFin(new java.sql.Date(System.currentTimeMillis()));
        // cambiamos el estado
        comanda.setEstado(Comanda.Estado.cerrada);

        return repositorioComanda.save(comanda);
    }

    // Eliminar comanda
    public void eliminarComanda(Integer id){
        repositorioComanda.deleteById(id);
    }

    // Mostrar todas las comandas
    public List<Comanda> verComandas(){
        return repositorioComanda.findAll();
    }

    // Mostrar las comandas de una fecha concreta
    public List<Comanda> buscarComanda(Date fecha){
        return repositorioComanda.findByFechaInicio(fecha);
    }
}