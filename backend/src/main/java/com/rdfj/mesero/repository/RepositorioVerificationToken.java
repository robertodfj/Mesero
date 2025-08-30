package com.rdfj.mesero.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rdfj.mesero.entity.VerificationToken;


public interface RepositorioVerificationToken extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> finByToken(String token);
} 
