package com.rdfj.mesero.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdfj.mesero.dto.DetalleComandaDTO;
import com.rdfj.mesero.entity.DetalleComanda;
import com.rdfj.mesero.mapper.DetalleComandaMapper;
import com.rdfj.mesero.repository.RepositorioDetalleComanda;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/detalles")
public class DetalleComandaControlador {
    
    @Autowired
    private RepositorioDetalleComanda repositorioDetalle;

    // Añadir detalle
    @PostMapping("/crear")
    public ResponseEntity<?> crear(@Valid @RequestBody DetalleComandaDTO detalleComandaDTO){
        try {
            DetalleComanda detalleComanda = DetalleComandaMapper.dtoToDetalleComanda(detalleComandaDTO);
            DetalleComanda nuevoDetalleComanda = repositorioDetalle.save(detalleComanda);

            DetalleComandaDTO nuevoDetalleComandaDTO = DetalleComandaMapper.detalleComandaToDTO(nuevoDetalleComanda);

            return ResponseEntity.ok(nuevoDetalleComandaDTO);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear detalle: " +e.getMessage());
        }
    }

    // Eliminar detalle 
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id){
        try {
            Optional<DetalleComanda> detalleComanda = repositorioDetalle.findById(id);
            if (detalleComanda.isEmpty()) {
                return ResponseEntity.status(404).body("El detalle no existe");
            }
            repositorioDetalle.delete(detalleComanda.get());
            return ResponseEntity.ok("Detalle eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al borrar detalle: " +e.getMessage());
        }
    }

    // Mostrar todos
    @GetMapping
    public ResponseEntity<?> mostrarTodas(){
        try {
            List<DetalleComanda> detalleComanda = repositorioDetalle.findAll();
            if (detalleComanda.isEmpty()) {
                return ResponseEntity.status(200).body("No se encuentran detalles");
            }
            List<DetalleComandaDTO> detalleComandaDTO = detalleComanda.stream()
                .map(DetalleComandaMapper::detalleComandaToDTO)
                .collect(Collectors.toList());
            return ResponseEntity.ok(detalleComandaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al buscar detalles: " +e.getMessage());
        }

    }

    // Mostrar por id
    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarId(@PathVariable Integer id){
        try {
            Optional<DetalleComanda> detalleComanda = repositorioDetalle.findById(id);
            if (detalleComanda.isEmpty()) {
                return ResponseEntity.status(404).body("Detalle no encontrado");
            }
            
            DetalleComandaDTO detalleComandaDTO = DetalleComandaMapper
                .detalleComandaToDTO(detalleComanda.get());

            return ResponseEntity.ok(detalleComandaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al buscar detalle por id: " +e.getMessage());
        }
    }
}
