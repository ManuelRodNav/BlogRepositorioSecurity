package com.sistemablogspring.sistema_blog_springboot_api_rest.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.Comentario;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO {    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    private String nombre;
    @NotEmpty(message = "el email no puede estar vacio")
    @Email(message = "no tiene formato de email")

    private String email;
    @NotEmpty
    @Size(min = 2,message = "el mensaje tiene que tener al menos 2 caracteres")
    private String  cuerpo;
    
    private Set<Comentario> comentarios;
    
}
