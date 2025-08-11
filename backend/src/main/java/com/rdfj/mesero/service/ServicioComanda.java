package com.rdfj.mesero.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Comanda;
import com.rdfj.mesero.entity.DetalleComanda;
import com.rdfj.mesero.repository.RepositorioComanda;
import com.rdfj.mesero.repository.RepositorioDetalleComanda;

@Service
public class ServicioComanda {
    
    @Autowired
    private RepositorioComanda repositorioComanda;

    @Autowired
    private RepositorioDetalleComanda repositorioDetalleComanda;

    // Añadir comanda
    public Comanda añadirComanda(Comanda comanda){
        comanda.setTotal(0.0);
        return repositorioComanda.save(comanda);
    }

    // Añadir linea comanda (Detalle comanda; lo que se va pidiendo)
    public Comanda añadirDetalle(Integer idComanda, DetalleComanda detalleComanda){
        Comanda comanda = repositorioComanda.findById(idComanda)
                .orElseThrow(() -> new RuntimeException("Comanda no encontrada"));

        // Vinculamos el detalle comanda a la comanda
        detalleComanda.setComanda(comanda);

        // Guardamos el nuevo detalle comanda / pedido
        repositorioDetalleComanda.save(detalleComanda);

        // Calculamos el nuevo total
        double total = comanda.getDetalles().stream()
            .mapToDouble(DetalleComanda::getSubtotal)
            .sum();

        return repositorioComanda.save(comanda);

    }

    // Eliminar Comanda
    public void eliminarComanda(Integer id){
        repositorioComanda.deleteById(id);
    }

    // Mostrar todas las comandas
    public List<Comanda> verComandas(){
        return repositorioComanda.findAll();
    }

    // Mostrar las comandas de fecha inicio
    public Optional<Comanda> buscarComanda(Date fecha){
        return repositorioComanda.findByFechaInicio(fecha);
    }
}
