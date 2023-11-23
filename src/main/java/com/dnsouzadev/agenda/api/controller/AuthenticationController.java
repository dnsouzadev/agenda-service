package com.dnsouzadev.agenda.api.controller;

import com.dnsouzadev.agenda.config.TokenService;
import com.dnsouzadev.agenda.domain.DTO.AuthenticationDTO;
import com.dnsouzadev.agenda.domain.DTO.LoginResponseDTO;
import com.dnsouzadev.agenda.domain.DTO.RegisterDTO;
import com.dnsouzadev.agenda.domain.entity.Usuario;
import com.dnsouzadev.agenda.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UsuarioRepository repository;
    private TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public  ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByUsuario(data.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        this.repository.save(new Usuario(data.name(), data.username(), encryptedPassword));

        return ResponseEntity.ok().build();
    }
}
