package com.rdfj.mesero.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.dto.ComandaDTO;
import com.rdfj.mesero.dto.DetalleComandaDTO;
import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Comanda;
import com.rdfj.mesero.entity.DetalleComanda;
import com.rdfj.mesero.entity.Mesa;
import com.rdfj.mesero.entity.Usuario;
import com.rdfj.mesero.mapper.DetalleComandaMapper;
import com.rdfj.mesero.repository.RepositorioComanda;
import com.rdfj.mesero.repository.RepositorioMesa;
import com.rdfj.mesero.repository.RepositorioUsuario;

@Service
public class ServicioComanda {
    
    @Autowired
    private RepositorioComanda repositorioComanda;

    @Autowired
    private ServicioDetalleComanda servicioDetalleComanda;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioMesa repositorioMesa;

    public Comanda añadirComanda(ComandaDTO dto) {
        // 1. Usuario autenticado
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = repositorioUsuario.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Obtenemos el bar del usuario
        Bar bar = usuario.getBar();

        // 3. Buscamos la mesa dentro de ese bar
        Mesa mesa = repositorioMesa.findByNumeroMesaAndBarId(dto.getNumeroMesa(), bar.getId())
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada en tu bar"));

        // 4. Creamos la comanda
        Comanda comanda = new Comanda();
        comanda.setMesa(mesa);
        comanda.setBar(bar);
        comanda.setFechaInicio(new java.sql.Date(System.currentTimeMillis()));
        comanda.setEstado(Comanda.Estado.abierta);
        comanda.setTotal(0.0);

        // 5. Si vienen detalles, añadimos cada detalle y actualizamos total
        if (dto.getDetalles() != null) {
            for (DetalleComandaDTO d : dto.getDetalles()) {
                DetalleComanda detalle = DetalleComandaMapper.dtoToDetalleComanda(d);
                detalle.setComanda(comanda);
                servicioDetalleComanda.añadirDetalleComanda(detalle);
            }
            comanda.setTotal(comanda.calcularTotal());
        }

        return repositorioComanda.save(comanda);
    }

    // Añadir detalle a una comanda existente
    public Comanda añadirDetalle(Integer idComanda, DetalleComanda detalleComanda) {
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
    public void eliminarComanda(Integer id) {
        repositorioComanda.deleteById(id);
    }

    // Mostrar todas las comandas
    public List<Comanda> verComandas() {
        return repositorioComanda.findAll();
    }

    // Mostrar las comandas de una fecha concreta
    public List<Comanda> buscarComanda(Date fecha) {
        return repositorioComanda.findByFechaInicio(fecha);
    }
}