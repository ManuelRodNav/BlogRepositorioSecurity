package com.sistemablogspring.sistema_blog_springboot_api_rest.Servicio;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sistemablogspring.sistema_blog_springboot_api_rest.DTO.PublicacionDTO;
import com.sistemablogspring.sistema_blog_springboot_api_rest.DTO.PublicacionRespuesta;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.Publicacion;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Exceptions.ResourceNotFound;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Repository.PublicacionRepository;

@Service
public class PublicacionServiceImp implements PublicacionServicio {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicaciondto) {
        Publicacion publicacion = mapearentidad(publicaciondto);

        Publicacion newPublicacion = publicacionRepository.save(publicacion);
        PublicacionDTO publicacionrespuesta = mapearDTO(newPublicacion);
        return publicacionrespuesta;
    }

    @Override
    public PublicacionRespuesta obtenerTodasLasPublicaciones(int numerodePagina, int medidadepagina, String ordenarpor, String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?Sort.by(ordenarpor).ascending() : Sort.by(ordenarpor).descending();
        Pageable pageable = PageRequest.of(numerodePagina, medidadepagina,sort);
        Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);
        List<Publicacion> publicacions = publicaciones.getContent();
        List<PublicacionDTO> contenido = publicacions.stream().map(publicmap -> mapearDTO(publicmap))
                .collect(Collectors.toList());
        PublicacionRespuesta publicacionRespuesta = new PublicacionRespuesta();
        publicacionRespuesta.setContenidos(publicacions);
        publicacionRespuesta.setNumeropaginas(numerodePagina);
        publicacionRespuesta.setMedidadepagina(medidadepagina);
        publicacionRespuesta.setTotaldePaginas(publicaciones.getTotalPages());
        publicacionRespuesta.setTotaleelementos(publicaciones.getTotalElements());
        publicacionRespuesta.setUltima(publicaciones.isLast());

        return publicacionRespuesta;
    }

    public PublicacionDTO mapearDTO(Publicacion reqpublicacion) {
        PublicacionDTO publicacionDTO = modelMapper.map(reqpublicacion, PublicacionDTO.class);
        return publicacionDTO;

    }

    public Publicacion mapearentidad(PublicacionDTO reqpublicacion) {
        Publicacion publicacion = modelMapper.map(reqpublicacion, Publicacion.class);
        return publicacion;

    }

    @Override
    public PublicacionDTO obtenerPublicacionID(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("publicacion", "id", "", id));

        return mapearDTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacionDTO(Long id, PublicacionDTO publicacionDTO) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("publicacion", "id", "", id));
        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        Publicacion publicacion2 = publicacionRepository.save(publicacion);
        return mapearDTO(publicacion2);

    }

    @Override
    public void eliminarPublicacionDTO(Long id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("publicacion", "id", "", id));
        publicacionRepository.delete(publicacion);
    }

}
