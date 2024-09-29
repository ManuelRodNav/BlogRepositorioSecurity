package com.sistemablogspring.sistema_blog_springboot_api_rest.Configuracion.Auth;

import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "users")
public class RegisterRequest {
    private String username;
    private String mail;
    private String password;
    private String firstname;
    private String lastname;
    private String country;
}
