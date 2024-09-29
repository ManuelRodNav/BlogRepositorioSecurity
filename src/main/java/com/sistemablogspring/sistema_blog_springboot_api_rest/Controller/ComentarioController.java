package com.sistemablogspring.sistema_blog_springboot_api_rest.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sistemablogspring.sistema_blog_springboot_api_rest.DTO.ComentarioDTO;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Servicio.ComentarioServiceIMP;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
@RequestMapping("/comentarios")
public class ComentarioController {



    
@Autowired
private ComentarioServiceIMP comentarioServiceIMP;

@GetMapping("/{publicacionid}")
public List<ComentarioDTO> listarComentarios(@PathVariable("publicacionid") Long id){
   return  comentarioServiceIMP.listartodosloscomentarios(id);

}

@GetMapping("/{publicacionid}/{id}")
public ResponseEntity<ComentarioDTO> buscComentarioDTO(@PathVariable("id") Long id, @PathVariable("publicacionid") Long idpublicacion){
    return new ResponseEntity(comentarioServiceIMP.buscarcomentario(idpublicacion, id),HttpStatus.OK);
}
@PostMapping("/{publicacionid}")
public ResponseEntity<ComentarioDTO> crearComentarioDTO( @Valid @RequestBody ComentarioDTO comentarioDTO, @PathVariable("publicacionid") Long id){
    return new ResponseEntity<ComentarioDTO>(comentarioServiceIMP.creaComentario(id, comentarioDTO), HttpStatus.OK);
} 
@PutMapping("/{comentarioid}")
public ResponseEntity<ComentarioDTO> actualizarComentario( @Valid @RequestBody ComentarioDTO comentarioDTO, @PathVariable("comentarioid") Long id){
    
    return new ResponseEntity<ComentarioDTO>(comentarioServiceIMP.actualizarcomentario(id, comentarioServiceIMP.mapearEntidad(comentarioDTO)), HttpStatus.OK);

}
@DeleteMapping("/{publicacionid}/{comentarioid}")
public ResponseEntity borrarcomentario(@PathVariable("publicacionid") Long id, @PathVariable("comentarioid") Long comentid){
    comentarioServiceIMP.borrarComentario(id, comentid);
    return new ResponseEntity("Borrado exitosamente", HttpStatus.OK);
}


}
