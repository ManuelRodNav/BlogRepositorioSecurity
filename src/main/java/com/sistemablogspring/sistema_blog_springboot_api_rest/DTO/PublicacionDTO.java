package com.sistemablogspring.sistema_blog_springboot_api_rest.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PublicacionDTO {

    private Long id;
    @NotEmpty
    @Size(min = 2,message = "el titulo de la publicacion no puede estar vacio")
    private String  titulo;

    @NotEmpty
    @Size(min = 10,message = "La descripcion debe tener al menos 10 caracteres")
    private String  descripcion;

    @NotEmpty
    private String contenido;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    
    
}
