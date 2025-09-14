package com.rdfj.mesero.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * Registro de usuario con token de verificación
     */
    @Transactional
    public String registro(String nombre, String email, String password, String rol, Integer idBar) {
        if (repositorioUsuario.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("El email indicado ya está registrado");
        }

        Bar bar = repositorioBar.findById(idBar)
            .orElseThrow(() -> new IllegalArgumentException("El bar indicado no existe"));

        Rol rolEnum;
        try {
            rolEnum = Rol.valueOf(rol.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rol inválido: " + rol);
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(passwordEncoder.encode(password));
        nuevoUsuario.setBar(bar);
        nuevoUsuario.setRol(rolEnum);
        nuevoUsuario.setEnabled(false);

        repositorioUsuario.saveAndFlush(nuevoUsuario);

        // Generar token de verificación
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUsuario(nuevoUsuario);
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));

        repositorioVerificationToken.save(verificationToken);

        // TODO: Enviar correo electronico con el token, de momento se imprime en consola
        System.out.println(token);
        return token;
    }

    /**
     * Verifica un token de activación y habilita el usuario
     */
    @Transactional
    public void verificarToken(String token) {
        VerificationToken verificationToken = repositorioVerificationToken.findByToken(token)
            .orElseThrow(() -> new IllegalArgumentException("El token es inválido"));

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("El token ha expirado");
        }

        Usuario usuario = verificationToken.getUsuario();
        usuario.setEnabled(true);
        repositorioUsuario.saveAndFlush(usuario);

        // Eliminamos el token usado
        repositorioVerificationToken.delete(verificationToken);
    }

    /**
     * Reenvio del token de verificacion
     */

    @Transactional
    public String generarNuevoToken(String email){
        Usuario usuario = repositorioUsuario.findByEmail(email)
            .orElseThrow(()-> new RuntimeException("No existe usuario con ese email"));

        if (usuario.isEnabled()) {
            throw new RuntimeException("El usuario ya esta verificado");
        }

        // Eliminar antiguos token
        repositorioVerificationToken.deleteAllbyUsuario(usuario);

        // Generar token de verificación
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUsuario(usuario);
        verificationToken.setExpiryDate(LocalDateTime.now().plusMinutes(30));

        repositorioVerificationToken.save(verificationToken);

        // TODO: Enviar correo electronico con el token, de momento se imprime en consola
        System.out.println(token);
        return token;
    }
    /**
     * Login de usuario con generación de JWT
     */
    public String login(String email, String password) {
        Usuario usuario = repositorioUsuario.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("El email no está registrado"));

        if (!usuario.isEnabled()) {
            throw new IllegalStateException("Debes verificar tu email para acceder");
        }

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );

        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(email);
        } else {
            throw new IllegalArgumentException("Credenciales inválidas");
        }
    }
}