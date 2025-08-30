package com.rdfj.mesero.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rdfj.mesero.entity.Bar;
import com.rdfj.mesero.entity.Usuario;
import com.rdfj.mesero.entity.VerificationToken;
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
        if (repositorioUsuario.findByEmail(email).isPresent()) {
            throw new RuntimeException("El email indicado ya está registrado");
        }

        Bar bar = repositorioBar.findByNombre(nombreBar)
            .orElseThrow(() -> new RuntimeException("El bar indicado no existe"));

        Rol rolEnum;
        try {
            rolEnum = Rol.valueOf(rol.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Rol inválido: " + rol);
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        nuevoUsuario.setBar(bar);
        nuevoUsuario.setRol(rolEnum);
        nuevoUsuario.setEnabled(false);

        repositorioUsuario.save(nuevoUsuario);

        // Generar token de verificación
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUsuario(nuevoUsuario);
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));

        repositorioVerificationToken.save(verificationToken);

        System.out.println("Token de verificación = " + token);
    }

    // Verificar token
    public void verificarToken(String token){
        VerificationToken verificationToken = repositorioVerificationToken.findByToken(token)
            .orElseThrow(() -> new RuntimeException("El token es inválido"));

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("El token ha expirado");
        }

        Usuario usuario = verificationToken.getUsuario();
        usuario.setEnabled(true);
        repositorioUsuario.save(usuario);

        repositorioVerificationToken.delete(verificationToken);
    }

    // Login
    public String login(String email, String password){
        Usuario usuario = repositorioUsuario.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("El email no está registrado"));

        if (!usuario.isEnabled()) {
            throw new RuntimeException("Debes verificar tu email para acceder");
        }

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );

        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(email);
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}