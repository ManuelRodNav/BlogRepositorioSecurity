package com.sistemablogspring.sistema_blog_springboot_api_rest;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SistemaBlogSpringbootApiRestApplication {


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();	
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaBlogSpringbootApiRestApplication.class, args);
	}


	
}
