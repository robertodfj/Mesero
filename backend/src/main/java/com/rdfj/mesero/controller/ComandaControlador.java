package com.rdfj.mesero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdfj.mesero.dto.ComandaDTO;
import com.rdfj.mesero.entity.Comanda;
import com.rdfj.mesero.mapper.ComandaMapper;
import com.rdfj.mesero.repository.RepositorioComanda;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comanda")
public class ComandaControlador {

    @Autowired
    private RepositorioComanda repositorioComanda;

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
    
}
