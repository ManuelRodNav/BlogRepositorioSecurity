package com.sistemablogspring.sistema_blog_springboot_api_rest.Configuracion.Auth;

import java.net.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistemablogspring.sistema_blog_springboot_api_rest.Configuracion.Role;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.User;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Repository.UserRepository;
@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())) ;
        UserDetails userDetails= userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String token= jwtService.getToken(userDetails);
        return AuthResponse.builder().token(token).build();
    }


    public AuthResponse register(RegisterRequest registerRequest){
        User user= User.builder().username(registerRequest.getUsername()).password(passwordEncoder.encode(  registerRequest.getPassword())).mail(registerRequest.getMail())
        .role(Role.USUARIO).build();
        userRepository.save(user);  
        return AuthResponse.builder().token(jwtService.getToken(user)).build();
    }
}
