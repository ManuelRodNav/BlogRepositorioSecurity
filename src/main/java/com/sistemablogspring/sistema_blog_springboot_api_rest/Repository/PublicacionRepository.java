package com.sistemablogspring.sistema_blog_springboot_api_rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.Publicacion;
import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long>{
    List<Publicacion> findByTitulo(String titulo);
}
