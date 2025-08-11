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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdfj.mesero.dto.MesaDTO;
import com.rdfj.mesero.entity.Mesa;
import com.rdfj.mesero.mapper.MesaMapper;
import com.rdfj.mesero.repository.RepositorioMesa;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mesa")
public class MesaControlador {
    
    @Autowired
    private RepositorioMesa repositorioMesa;


    // Añadir mesa
    @PostMapping("/crear")
    public ResponseEntity<?> añadirMesa(@Valid @RequestBody MesaDTO mesaDTO){

        try {
            Mesa mesa = MesaMapper.dtoToMesa(mesaDTO);
            Mesa nuevaMesa = repositorioMesa.save(mesa);
            MesaDTO nuevaMesaDTO = MesaMapper.mesaToDTO(nuevaMesa);
            return ResponseEntity.ok(nuevaMesaDTO);
        } catch (Exception e) {
           return ResponseEntity.status(500).body("Error al añadir mesa" + e.getMessage());
        }

    }

    // Eliminar mesa
    @DeleteMapping("/eliminar/{numeroMesa}")
    public ResponseEntity<?> eliminarMesa(@PathVariable int numeroMesa){
        try {
            repositorioMesa.deleteByNumeroMesa(numeroMesa);
            return ResponseEntity.ok("Mesa eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al eliminar mesa" + e.getMessage());
        }
    }

    // Mostrar todas
    @GetMapping
    public ResponseEntity<?> mostrarTodas(){
        try {
            List<Mesa> mesas = repositorioMesa.findAll();
            List<MesaDTO> mesaDTO = mesas.stream()
                .map(MesaMapper::mesaToDTO)
                .collect(Collectors.toList());
            return ResponseEntity.ok(mesaDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("No se encuentran mesas" +e.getMessage());
        }
    }
    
    // Editar estado (enum)
    @PutMapping("/editar/estado/{numeroMesa}")
    public ResponseEntity<?> editarEstado(@PathVariable int numeroMesa, @RequestBody MesaDTO mesaDTO){
        try {
            Optional<Mesa> optMesa = repositorioMesa.findByNumeroMesa(numeroMesa);

            if (optMesa.isPresent()) {
                Mesa mesa = optMesa.get();

                mesa.setEstado(Mesa.Estado.valueOf(mesaDTO.getEstado().name()));

                repositorioMesa.save(mesa);
                return ResponseEntity.ok("Estado de mesa editada con exito");
            } else{
                return ResponseEntity.status(400).body("Mesa no encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al editar estado de mesa" + e.getMessage());
        }
    }

    // Editar capacidad (int)
    @PutMapping("/editar/capacidad/{numeroMesa}")
    public ResponseEntity<?> editarCapacidad(@PathVariable int numeroMesa, @RequestBody MesaDTO mesaDTO){
        try {
            Optional<Mesa> optMesa = repositorioMesa.findByNumeroMesa(numeroMesa);

            if (optMesa.isPresent()) {
                Mesa mesa = optMesa.get();

                mesa.setCapacidad(mesaDTO.getCapacidad());

                repositorioMesa.save(mesa);
                return ResponseEntity.ok("Capacidad de mesa editada con exito");
            } else{
                return ResponseEntity.status(400).body("Mesa no encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al editar capacidad de mesa" + e.getMessage());
        }
    }
}
