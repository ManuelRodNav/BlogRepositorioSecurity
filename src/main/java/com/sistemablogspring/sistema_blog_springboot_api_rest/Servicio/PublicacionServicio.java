package com.sistemablogspring.sistema_blog_springboot_api_rest.Servicio;

import com.sistemablogspring.sistema_blog_springboot_api_rest.DTO.PublicacionDTO;
import com.sistemablogspring.sistema_blog_springboot_api_rest.DTO.PublicacionRespuesta;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.Publicacion;
import com.sistemablogspring.sistema_blog_springboot_api_rest.DTO.PublicacionRespuesta;
public interface PublicacionServicio {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicaciondto);
    public PublicacionRespuesta obtenerTodasLasPublicaciones(int numerodePagina, int medidadepagina, String ordenarpor, String sortDir);
    public PublicacionDTO obtenerPublicacionID(Long id);
    public PublicacionDTO actualizarPublicacionDTO(Long id, PublicacionDTO publicacionDTO);
    public void eliminarPublicacionDTO(Long id);
}
