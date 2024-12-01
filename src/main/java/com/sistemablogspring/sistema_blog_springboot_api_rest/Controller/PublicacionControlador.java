package com.sistemablogspring.sistema_blog_springboot_api_rest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemablogspring.sistema_blog_springboot_api_rest.DTO.PublicacionDTO;
import com.sistemablogspring.sistema_blog_springboot_api_rest.DTO.PublicacionRespuesta;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.Publicacion;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Servicio.PublicacionServiceImp;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Servicio.PublicacionServicio;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Utils.AppConstants;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;

@RestController
@RequestMapping("/api/publicacion")
@RequiredArgsConstructor
public class PublicacionControlador {
    @Autowired
    private PublicacionServiceImp publicacionServiceImp;

    @GetMapping("demo")
    @ResponseBody
    public String demopureba(){
        return "Demo funciona correctamente";
    }
    @PostMapping
    public ResponseEntity<PublicacionDTO> guardarpublicacion( @Valid @RequestBody PublicacionDTO publicacionDTO) {
        return new ResponseEntity<>(publicacionServiceImp.crearPublicacion(publicacionDTO), HttpStatus.OK);
    }

    @GetMapping("/all")
    public PublicacionRespuesta listartodaslaspublicaciones(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.NUMERO_DE_PAGINA_POR_DEFECTO, required = false) int numerodePagina,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.NUMERO_DE_ELEMENTOS_POR_PAGINIA, required = false) int medidadepagina,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.ORDENAR_POR_DEFECTO, required = false) String ordenarPor,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.ORDENAR_DIRECCION_POR_DEFECTO, required = false) String sortDir) {
                System.out.println("Metodo correctamente ejecutado");
        return publicacionServiceImp.obtenerTodasLasPublicaciones(numerodePagina, medidadepagina, ordenarPor, sortDir);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> buscarporId(@PathVariable Long id) {
        return ResponseEntity.ok(publicacionServiceImp.obtenerPublicacionID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> actualizarporid( @PathVariable Long id,  @Valid @RequestBody PublicacionDTO reqDto) {
        PublicacionDTO publicacionDTO = publicacionServiceImp.actualizarPublicacionDTO(id, reqDto);
        return new ResponseEntity<>(publicacionDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarpublicacion(@PathVariable("id") Long id) {
        publicacionServiceImp.eliminarPublicacionDTO(id);
        return new ResponseEntity<>("Se ha borrado exitosamente", HttpStatus.OK);
    }

}
