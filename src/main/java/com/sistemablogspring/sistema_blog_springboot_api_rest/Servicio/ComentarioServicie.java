package com.sistemablogspring.sistema_blog_springboot_api_rest.Servicio;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.Comentario;
import com.sistemablogspring.sistema_blog_springboot_api_rest.DTO.ComentarioDTO;
import java.util.List;
public interface ComentarioServicie {

    public ComentarioDTO creaComentario(Long publicacion_id, ComentarioDTO comentariodto);
    public void borrarComentario(Long publicacion_id,Long comentario_id);
    public ComentarioDTO actualizarcomentario(Long comentid,Comentario comentario);
    public ComentarioDTO buscarcomentario(Long publicacionID, Long comentarioid);
    public List<ComentarioDTO> listartodosloscomentarios(Long publicacionid);
}
