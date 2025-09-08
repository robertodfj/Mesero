package com.rdfj.mesero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rdfj.mesero.dto.LoginDTO;
import com.rdfj.mesero.dto.RegistrarUsuarioDTO;
import com.rdfj.mesero.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Registro de usuarios
    @PostMapping("/register")
    public ResponseEntity<?> registrar(@RequestBody RegistrarUsuarioDTO dto){
        try {
            authService.registro(
                dto.getNombre(), 
                dto.getEmail(), 
                dto.getPassword(), 
                dto.getRol(), 
                dto.getBarId());

            return ResponseEntity.ok("Usuario registrado correctamente. Revisa el correo para verificar");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar al usuario: " +e.getMessage());
        }
    }

    // Verificar email
    @PostMapping("/verificar")
    public ResponseEntity<?> verificar(@RequestBody String token){
        try {
            authService.verificarToken(token);
            return ResponseEntity.ok("Usuario verificado. Ya puedes iniciar sesion");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al verificar token: " +e.getMessage());
        }
    }
    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(LoginDTO dto){
        try {
            String JWT = authService.login(dto.getEmail(), dto.getPassword());

            return ResponseEntity.ok(JWT);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al iniciar sesion: " +e.getMessage());
        }
    }
}