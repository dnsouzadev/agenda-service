package com.dnsouzadev.agenda.config;


import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationFilterConfig extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String usuario = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
        String senha = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);

        log.info("Usuario: {}", usuario);
        log.info("Senha: {}", senha);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(usuario, senha);

        return authenticationManager.authenticate(authToken);
    }

}
