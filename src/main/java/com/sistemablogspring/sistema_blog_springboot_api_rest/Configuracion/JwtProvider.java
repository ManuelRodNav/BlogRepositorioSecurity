package com.sistemablogspring.sistema_blog_springboot_api_rest.Configuracion;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity.JwtSpec;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.web.client.RestTemplate;

import com.sistemablogspring.sistema_blog_springboot_api_rest.JWT.JwtAuthFilter;

import lombok.RequiredArgsConstructor;
import lombok.Value;


@Configuration
@RequiredArgsConstructor
public class JwtProvider{

    
    private final JwtAuthFilter jwtAuthFilter;

    
    private final AuthenticationProvider provider;

     
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();
        return httpSecurity.authorizeRequests(authorizeRequests-> authorizeRequests.requestMatchers("auth/**").permitAll().
        requestMatchers(HttpMethod.GET).permitAll().anyRequest().authenticated())
        .sessionManagement(sessionManagement-> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(provider).addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class)
        .build();
    }
    }

