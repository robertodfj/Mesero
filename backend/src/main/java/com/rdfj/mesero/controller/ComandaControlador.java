package com.rdfj.mesero.controller;

import java.sql.Date;
import java.util.List;
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

import com.rdfj.mesero.dto.ComandaDTO;
import com.rdfj.mesero.entity.Comanda;
import com.rdfj.mesero.entity.DetalleComanda;
import com.rdfj.mesero.mapper.ComandaMapper;
import com.rdfj.mesero.repository.RepositorioComanda;
import com.rdfj.mesero.service.ServicioComanda;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comanda")
public class ComandaControlador {

    @Autowired
    private RepositorioComanda repositorioComanda;

    @Autowired
    private ServicioComanda servicioComanda;

    // Añadir comanda
    @PostMapping("/crear")
    public ResponseEntity<?> añadirComanda(@Valid @RequestBody ComandaDTO comandaDTO){
        try {
            Comanda comanda = ComandaMapper.dtoToComanda(comandaDTO);
            Comanda nuevaComanda = repositorioComanda.save(comanda);
            ComandaDTO nuevaComandaDTO = ComandaMapper.comandaToDTO(nuevaComanda);
            return ResponseEntity.ok(nuevaComandaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al añadir comanda");
        }
    }

    // Editar comanda (Añadiendo (detalleComanda)
    @PostMapping("/{idComanda}/detalle")
    public ResponseEntity<?> añadirDetalle(@PathVariable Integer idComanda, @RequestBody DetalleComanda detalleComanda){
        try {
            Comanda comandaActualizada = servicioComanda.añadirDetalle(idComanda, detalleComanda);
            return ResponseEntity.ok(comandaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // Eliminar comanda
    @DeleteMapping("/{idComanda}/borrar")
    public ResponseEntity<?> eliminarComanda(@PathVariable Integer idComanda){
        try {
            repositorioComanda.deleteById(idComanda);
            return ResponseEntity.ok("Comanda eliminada");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar comanda" + e.getMessage());
        }
    }

    // Mostar por fecha
    
    // Mostrar todas
    @GetMapping("/todas")
    public ResponseEntity<?> mostrarTodas(){
        try {
            List<Comanda> comanda = repositorioComanda.findAll();
            List<ComandaDTO> comandaDTO = comanda.stream()
                .map(ComandaMapper::comandaToDTO)
                .collect(Collectors.toList());
            return ResponseEntity.ok(comandaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("No se encuentan comandas");
        }
    }
    
}
