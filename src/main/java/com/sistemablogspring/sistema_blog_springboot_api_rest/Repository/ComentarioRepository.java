package com.sistemablogspring.sistema_blog_springboot_api_rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Long>{
    public List<Comentario> findByPublicacionId(Long id);
}
