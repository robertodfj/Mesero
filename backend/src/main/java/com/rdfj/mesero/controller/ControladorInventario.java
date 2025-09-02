package com.rdfj.mesero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdfj.mesero.dto.ActualizarStockDTO;
import com.rdfj.mesero.service.ServicioInventario;

@RestController
@RequestMapping("/inventario")
public class ControladorInventario {
    
    @Autowired
    private ServicioInventario servicioInventario;

    // Mostrar inventario
    @GetMapping
    public ResponseEntity<?> mostrarInventario(){
        try {
            return ResponseEntity.ok(servicioInventario.mostrarInventario());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al mostrar inventario: " +e.getMessage());
        }
    }

    // Editar inventario
    @PutMapping("/editar")
    public ResponseEntity<?> editarInventario(@RequestBody ActualizarStockDTO dto){
        try {
            return ResponseEntity.ok(
                servicioInventario.actualizarStock(
                    dto.getProductoId(), 
                    dto.getNuevaUnidad(), 
                    dto.getNuevaCantidad())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al editar el inventario: " +e.getMessage());
        }
    }
}
