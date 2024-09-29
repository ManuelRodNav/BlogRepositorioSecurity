package com.sistemablogspring.sistema_blog_springboot_api_rest.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data
 @AllArgsConstructor
 @NoArgsConstructor
public class ErrorDTO {

    private Date marcatiempo;
    private String message;
    private String detalles;

    
}
