package com.rdfj.mesero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Usuario;
import com.rdfj.mesero.entity.Usuario.Rol;
import com.rdfj.mesero.repository.RepositorioBar;
import com.rdfj.mesero.repository.RepositorioUsuario;
import com.rdfj.mesero.repository.RepositorioVerificationToken;
import com.rdfj.mesero.security.JwtUtil;

@Service
public class AuthService {
    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioBar repositorioBar;

    @Autowired
    private RepositorioVerificationToken repositorioVerificationToken;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // Registrar usuarios
    public void registro(String nombre, String email, String password, String rol, String nombreBar){
        // Verificamos si el email y el bar existen
        if (repositorioUsuario.findByEmail(email).isPresent()) {
            throw new RuntimeException("El email indicado ya esta registrado");
        }
        Bar bar = repositorioBar.findByNombre(nombreBar)
            .orElseThrow(() -> new RuntimeException("El bar indicado no existe"));

        // Convertimos el String a enum
        Rol rolEnum;
        try {
            rolEnum = Rol.valueOf("ROLE_" + rol.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Rol inválido: " + rol);
        }



        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        nuevoUsuario.setBar(null);
        nuevoUsuario.setRol(rolEnum);
        nuevoUsuario.setEnabled(false);
    }
}
