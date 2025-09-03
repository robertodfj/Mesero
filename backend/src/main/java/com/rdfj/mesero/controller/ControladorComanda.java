package com.rdfj.mesero.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdfj.mesero.dto.ComandaDTO;
import com.rdfj.mesero.dto.DetalleComandaDTO;
import com.rdfj.mesero.entity.Comanda;
import com.rdfj.mesero.entity.DetalleComanda;
import com.rdfj.mesero.mapper.ComandaMapper;
import com.rdfj.mesero.mapper.DetalleComandaMapper;
import com.rdfj.mesero.service.ServicioComanda;

@RestController
@RequestMapping("/comanda")
public class ControladorComanda {
    
    @Autowired
    private ServicioComanda servicioComanda;

    // Crear comanda
    @PostMapping("/abrir")
    public ResponseEntity<?> abirComanda(@RequestBody ComandaDTO dto){
        try {
            Comanda comanda = ComandaMapper.dtoToComanda(dto);
            servicioComanda.añadirComanda(comanda);
            return ResponseEntity.ok("Comanda abierta correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al abrir comanda: "+e.getMessage());
        }
    }

    // Añadir detalle
    @PostMapping("/{idComanda}/detalle")
    public ResponseEntity<?> añadirDetalle(@PathVariable int idComanda, @RequestBody DetalleComandaDTO dto){
        try {
            DetalleComanda detalleComanda = DetalleComandaMapper.dtoToDetalleComanda(dto);
            servicioComanda.añadirDetalle(idComanda, detalleComanda);
            return ResponseEntity.ok("Detalle añadido correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al añadir detalle: " +e.getMessage());
        }        
    }

    // Ver todas
    @GetMapping
    public ResponseEntity<?> verTodas(){
        try {
            return ResponseEntity.ok(servicioComanda.verComandas());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al mostrar comandas: "+e.getMessage());
        }
    }

    // Buscar por fechas
    @GetMapping("/{fecha}")
    public ResponseEntity<?> verPorFecha(@PathVariable Date fecha){
        try {
            return ResponseEntity.ok(servicioComanda.buscarComanda(fecha));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al buscar por fecha: " +e.getMessage());
        }
    }

    // Cerrar una comanda (calcula el total y cambia estado)
    @PostMapping("/{id}/cerrar")
    public ResponseEntity<?> cerrar(@PathVariable int id) {
        try {
            Comanda cerrada = servicioComanda.cerrarComanda(id);
            return ResponseEntity.ok("Comanda cerrada correctamente. Total: " + cerrada.getTotal());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al cerrar comanda: " + e.getMessage());
        }
    }

}
