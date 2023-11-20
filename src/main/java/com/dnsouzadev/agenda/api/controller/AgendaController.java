package com.dnsouzadev.agenda.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnsouzadev.agenda.api.request.AgendaRequest;
import com.dnsouzadev.agenda.api.response.AgendaResponse;
import com.dnsouzadev.agenda.domain.service.AgendaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService service;

    @GetMapping
    public ResponseEntity<AgendaResponse> listarTodos() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> buscarPorId(Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<AgendaResponse> salvar(@RequestBody AgendaRequest agenda) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaResponse> atualizar(Long id, AgendaResponse agenda) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(Long id) {
        return null;
    }

}
