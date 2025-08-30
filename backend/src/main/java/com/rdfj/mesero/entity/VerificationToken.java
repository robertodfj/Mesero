package com.rdfj.mesero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class VerificationToken {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String token;

        @OneToOne
        @JoinColumn(name = "usuario_id", referencedColumnName = "id")
        private Usuario usuario;

        //Constructor
        public VerificationToken(){}

        public VerificationToken(int id, String token, Usuario usuario){
            this.id = id;
            this.token = token;
            this.usuario = usuario;
        }

        // Getters y setters
        public int getId(){
            return id;
        }
        public void setId(int id){
            this.id = id;
        }

        public String getToken(){
            return token;
        }
        public void setToken(String token){
            this.token = token;
        }

        public Usuario getUsuario(){
            return usuario;
        }
        public void setUsuario(Usuario usuario){
            this.usuario = usuario;
        }
}
