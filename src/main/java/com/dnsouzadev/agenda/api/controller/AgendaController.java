package com.dnsouzadev.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnsouzadev.agenda.api.mapper.AgendaMapper;
import com.dnsouzadev.agenda.api.request.AgendaRequest;
import com.dnsouzadev.agenda.api.response.AgendaResponse;
import com.dnsouzadev.agenda.domain.entity.Agenda;
import com.dnsouzadev.agenda.domain.service.AgendaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService service;
    private final AgendaMapper mapper;

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> listAll() {
        List<Agenda> agendas = service.listAll();
        List<AgendaResponse> agendaResponseList = mapper.toAgendaResponseList(agendas);

        return ResponseEntity.ok(agendaResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> findById(@PathVariable Long id) {
        Optional<Agenda> optAgenda = service.findById(id);

        if (optAgenda.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AgendaResponse agendaResponse = mapper.toAgendaResponse(optAgenda.get());

        return ResponseEntity.ok(agendaResponse);
    }

    @PostMapping
    public ResponseEntity<AgendaResponse> save(@Valid @RequestBody AgendaRequest agenda) {
        Agenda agendaSalva = service.save(mapper.toAgenda(agenda));
        AgendaResponse agendaResponse = mapper.toAgendaResponse(agendaSalva);

        return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);
    }
}
