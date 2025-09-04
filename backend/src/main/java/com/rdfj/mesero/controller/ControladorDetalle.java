package com.rdfj.mesero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdfj.mesero.dto.DetalleComandaDTO;
import com.rdfj.mesero.entity.DetalleComanda;
import com.rdfj.mesero.mapper.DetalleComandaMapper;
import com.rdfj.mesero.service.ServicioDetalleComanda;

@RestController
@RequestMapping("/detalle")
public class ControladorDetalle {
    
    @Autowired
    private ServicioDetalleComanda servicioDetalleComanda;

    // Añadir detalle
    @PostMapping("/crear")
    public ResponseEntity<?> añadir(@RequestBody DetalleComandaDTO dto){
        try {
            DetalleComanda detalleComanda = DetalleComandaMapper.dtoToDetalleComanda(dto);
            servicioDetalleComanda.añadirDetalleComanda(detalleComanda);
            return ResponseEntity.ok("Detalle añadido de manera correcta");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear detalle: " +e.getMessage());
        }
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id){
        try {
            servicioDetalleComanda.borrarDetalleComanda(id);
            return ResponseEntity.ok("Detalle eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar detalle: " +e.getMessage());
        }
    }
}
