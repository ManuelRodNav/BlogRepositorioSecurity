package com.sistemablogspring.sistema_blog_springboot_api_rest.Servicio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.Publicacion;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Exceptions.BlogAppException;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Exceptions.ResourceNotFound;
import com.sistemablogspring.sistema_blog_springboot_api_rest.DTO.ComentarioDTO;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.Comentario;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Repository.ComentarioRepository;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Repository.PublicacionRepository;

@Service
public class ComentarioServiceIMP  implements ComentarioServicie{

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ComentarioDTO actualizarcomentario(Long comentid, Comentario comentario) {
        Comentario comentarioact= comentarioRepository.findById(comentid).orElseThrow(()-> new ResourceNotFound("no se ha podido encontrar el comentario", "404", "comentario no encontrado", comentid));
        comentarioact.setCuerpo(comentario.getCuerpo());
        comentarioact.setEmail(comentario.getEmail());
        comentarioact.setNombre(comentario.getNombre());
        comentarioact.setPublicacion(comentario.getPublicacion());
        comentarioRepository.save(comentarioact);
        return mapearComentarioDTO(comentarioact);
    }
    

    @Override
    public void borrarComentario(Long publicacion_id, Long comentario_id) {
        Publicacion publicacion= publicacionRepository.findById(publicacion_id).orElseThrow(()-> new ResourceNotFound("no encontrado","no se ha encontardo una publicacion con esa id", "recurso no encontrado",publicacion_id));
        Comentario comentariotofind= comentarioRepository.findById(comentario_id).orElseThrow(()-> new ResourceNotFound("no encontrado","no se ha encontardo un comentario con esa id", "recurso no encontrado",comentario_id));
    comentarioRepository.delete(comentariotofind);
    
    }

    @Override
    public ComentarioDTO creaComentario(Long publicacion_id,ComentarioDTO comentarioDTO) {
        Comentario comentario = mapearEntidad(comentarioDTO);
        Publicacion publicacion= publicacionRepository.findById(publicacion_id).orElseThrow(()-> new ResourceNotFound("no se ha encontrado","ninguna publicacion con esa id" ,"", publicacion_id));
        comentario.setPublicacion(publicacion);
        Comentario nuevComentario = comentarioRepository.save(comentario);
        return mapearComentarioDTO(nuevComentario);

        
    }

    public ComentarioDTO mapearComentarioDTO(Comentario comentario){
        ComentarioDTO comentarioDTO= modelMapper.map(comentario, ComentarioDTO.class);
        return comentarioDTO;
    }
    public Comentario mapearEntidad(ComentarioDTO comentariodto){
        Comentario comentario= modelMapper.map(comentariodto, Comentario.class);
        return comentario;
    }


    @Override
    public ComentarioDTO buscarcomentario(Long publicacionID, Long comentarioid) {
       Publicacion searchPublicacion =  publicacionRepository.findById(publicacionID)
       .orElseThrow(()-> new ResourceNotFound("no se ha encontrado la publicacion", " no se ha encontrado nada con la id", "", publicacionID));
       
       Comentario comentario= comentarioRepository.findById(comentarioid).orElseThrow(()-> new ResourceNotFound("no encontrado","no se ha encontardo un comentario con esa id", "recurso no encontrado",comentarioid));
       if(!comentario.getPublicacion().getId().equals(searchPublicacion.getId())){
        throw new BlogAppException(HttpStatus.BAD_REQUEST, "el comentario no pertenece a la publicacion","");
       }
        return mapearComentarioDTO(comentario);
    }


    @Override
    public List<ComentarioDTO> listartodosloscomentarios(Long publicacionid) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionid);
        return comentarios.stream()
                          .map(comentario -> mapearComentarioDTO(comentario))
                          .collect(Collectors.toList());
    }
    

    

    
    
    
}
