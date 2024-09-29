package com.sistemablogspring.sistema_blog_springboot_api_rest.DTO;
import java.util.List;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.Publicacion;
public class PublicacionRespuesta {
    private List<Publicacion> contenidos;
    private Integer numeropaginas;
    private Integer medidadepagina;
    public Integer getMedidadepagina() {
        return medidadepagina;
    }
    public void setMedidadepagina(Integer medidadepagina) {
        this.medidadepagina = medidadepagina;
    }
    private Long  totaleelementos;
    private Integer totaldePaginas;
    private Boolean ultima;
    public List<Publicacion> getContenidos() {
        return contenidos;
    }
    public Integer getNumeropaginas() {
        return numeropaginas;
    }
    public Long getTotaleelementos() {
        return totaleelementos;
    }
    public Integer getTotaldePaginas() {
        return totaldePaginas;
    }
    public Boolean getUltima() {
        return ultima;
    }
    public void setContenidos(List<Publicacion> contenidos) {
        this.contenidos = contenidos;
    }
    public void setNumeropaginas(Integer numeropaginas) {
        this.numeropaginas = numeropaginas;
    }
    public void setTotaleelementos(Long totaleelementos) {
        this.totaleelementos = totaleelementos;
    }
    public void setTotaldePaginas(Integer totaldePaginas) {
        this.totaldePaginas = totaldePaginas;
    }
    public void setUltima(Boolean ultima) {
        this.ultima = ultima;
    }
}
