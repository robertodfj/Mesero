package com.rdfj.mesero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdfj.mesero.dto.BarDTO;
import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.mapper.BarMapper;
import com.rdfj.mesero.service.ServicioBar;

@RestController
@RequestMapping("/bar")
public class ControladorBar {

    @Autowired
    private ServicioBar servicioBar;

    // Crear bar (Inventario ya dentro de este)
    @PostMapping("/crear")
    public ResponseEntity<?> crearBar(@RequestBody BarDTO dto){
        try {
            Bar bar = BarMapper.dtoToBar(dto);
            servicioBar.añadirBar(bar);

            return ResponseEntity.ok("Bar creado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el bar: " +e.getMessage());
        }
    }

    // Mostrar bar del usuario autenticado
    @GetMapping
    public ResponseEntity<?> mostrarBar(){
        try {
            String email = org.springframework.security.core.context.SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();
        if (email == null) {
            return ResponseEntity.status(404).body("Usuario no autenticado");
        }

        Bar bar = servicioBar.obtenerBarPorEmailUsuario(email);

        return ResponseEntity.ok(BarMapper.barToDTO(bar));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener bar: " +e.getMessage());
        }

    }
}
