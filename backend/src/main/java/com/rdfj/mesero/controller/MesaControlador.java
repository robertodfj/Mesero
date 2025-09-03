package com.rdfj.mesero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdfj.mesero.dto.MesaDTO;
import com.rdfj.mesero.entity.Mesa;
import com.rdfj.mesero.mapper.MesaMapper;
import com.rdfj.mesero.service.ServicioMesa;

@RestController
@RequestMapping("/mesa")
public class MesaControlador {
    
    @Autowired
    private ServicioMesa servicioMesa;

    // Crear mesa
    @PostMapping("/crear")
    public ResponseEntity<?> crearMesa(@RequestBody MesaDTO dto){
        try {
            Mesa mesa = MesaMapper.dtoToMesa(dto);
            servicioMesa.añadirMesa(mesa);
            return ResponseEntity.ok("Mesa creada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear mesa: " +e.getMessage());
        }
    }

    // Ver todas
    @GetMapping
    public ResponseEntity<?> mostrar(){
        try {
            return ResponseEntity.ok(servicioMesa.verMesas());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al mostrar mesas: "+e.getMessage());
        }
    }

    // Eliminar mesa
    @DeleteMapping({"/{numMesa}"})
    public ResponseEntity<?> eliminar(@PathVariable int numMesa){
        try {
            servicioMesa.eliminarPorNumeroMesa(numMesa);
            return ResponseEntity.ok("Mesa eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al borrar mesa: " +e.getMessage());
        }
    }
}
