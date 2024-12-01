package com.sistemablogspring.sistema_blog_springboot_api_rest.Configuracion;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import com.sistemablogspring.sistema_blog_springboot_api_rest.JWT.JwtAuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider provider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Aplicar CORS config
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/api/publicacion/**").authenticated()
                .anyRequest().authenticated() 
            
                ) 
                
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless para JWT
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Configuración de CORS para todo el backend
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        CorsConfiguration allConfiguration= new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200"); // Permitir solo desde localhost:4200 (tu frontend)
        configuration.addAllowedMethod("GET"); // Permitir método GET
        configuration.addAllowedMethod("POST"); // Permitir método POST
        configuration.addAllowedMethod("PUT"); // Permitir método PUT
        configuration.addAllowedMethod("DELETE"); // Permitir DELETE si lo necesitas
        configuration.addAllowedMethod("OPTIONS"); // Importante: Permitir método OPTIONS para preflight
        configuration.setAllowCredentials(true); // Permitir credenciales como cookies y JWT
        configuration.addAllowedHeader("Authorization"); // Permitir encabezado Authorization para JWT
        configuration.addAllowedHeader("Content-Type"); // Permitir Content-Type
        configuration.addAllowedHeader("Accept"); // Permitir Accept
        allConfiguration.addAllowedOrigin("http://localhost:4200");
        allConfiguration.addAllowedOriginPattern("/api/publicacion/all");
        allConfiguration.setAllowedMethods(List.of("GET","POST","DELETE","PUT"));
        allConfiguration.setAllowCredentials(true);
        
        
        // Exponer encabezados necesarios para el navegador
        configuration.addExposedHeader("Access-Control-Allow-Origin");
        configuration.addExposedHeader("Access-Control-Allow-Headers"); 
        allConfiguration.addExposedHeader("Access-Control-Allow-Origin");
        allConfiguration.addExposedHeader("Access-Control-Allow-Headers"); 
        
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        
        source.registerCorsConfiguration("/**", configuration);
        source.registerCorsConfiguration("/api/publicacion/all",allConfiguration);
        
        return source;
    }
}
