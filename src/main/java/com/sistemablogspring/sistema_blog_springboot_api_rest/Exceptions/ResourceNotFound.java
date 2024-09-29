package com.sistemablogspring.sistema_blog_springboot_api_rest.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import lombok.AllArgsConstructor;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
 private String nombredelrecurso;
 private String nombreCampo;
 private Long valordelcampo;
public ResourceNotFound(String message, String nombredelrecurso, String nombreCampo, Long valordelcampo) {
    super(String.format("%s no encontrado con : %s  :'%s",nombredelrecurso,nombreCampo,valordelcampo));
    this.nombredelrecurso = nombredelrecurso;
    this.nombreCampo = nombreCampo;
    this.valordelcampo = valordelcampo;
}


    
}
