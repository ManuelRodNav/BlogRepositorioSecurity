package com.sistemablogspring.sistema_blog_springboot_api_rest.Exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sistemablogspring.sistema_blog_springboot_api_rest.DTO.ErrorDTO;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDTO> manejarResourceNotFoundException(ResourceNotFound resourceNotFound,WebRequest webRequest){
        ErrorDTO error= new ErrorDTO(new Date(), resourceNotFound.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BlogAppException.class)
    public ResponseEntity<ErrorDTO> manejarBlogAppExceptions(BlogAppException blogAppException,WebRequest webRequest){
        ErrorDTO error= new ErrorDTO(new Date(), blogAppException.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> manejarGlobalAppExceptions(Exception exception,WebRequest webRequest){
        ErrorDTO error= new ErrorDTO(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> maperrores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((errores)->{
            String nombrecampo=((FieldError)errores).getField();
            String mensaje= errores.getDefaultMessage();
            maperrores.put(nombrecampo, mensaje);
        });
        return new ResponseEntity<Object>(maperrores, HttpStatus.BAD_REQUEST);
    }


    
}
