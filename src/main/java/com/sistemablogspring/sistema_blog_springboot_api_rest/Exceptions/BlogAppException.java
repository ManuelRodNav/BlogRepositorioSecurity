package com.sistemablogspring.sistema_blog_springboot_api_rest.Exceptions;

import org.springframework.http.HttpStatus;

public class BlogAppException  extends RuntimeException{
    private HttpStatus status;
    private String mensaje;
    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public BlogAppException(HttpStatus status,String  mensaje,String mensaje1) {
        super();
        this.status = status;
        this.mensaje = mensaje;
        this.mensaje= mensaje1;
    }

    
    
    
}
