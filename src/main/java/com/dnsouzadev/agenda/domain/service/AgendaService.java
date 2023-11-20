package com.dnsouzadev.agenda.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnsouzadev.agenda.domain.entity.Agenda;
import com.dnsouzadev.agenda.domain.entity.Paciente;
import com.dnsouzadev.agenda.domain.exception.BusinessException;
import com.dnsouzadev.agenda.domain.repository.AgendaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository repository;
    private final PacienteService pacienteService;

    public List<Agenda> listarTodos() {
        return repository.findAll();
    }

    public Optional<Agenda> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Agenda salvar(Agenda agenda) {

        Optional<Paciente> optPaciente = pacienteService.buscarPorId(agenda.getPaciente().getId());

        if (optPaciente.isEmpty()) {
            throw new BusinessException("Paciente não encontrado");
        }

        repository.findByHorario(agenda.getHorario())
                .ifPresent(agendamentoExistente -> {
                    throw new BusinessException("Já existe um agendamento para o horário informado");
                });

        agenda.setPaciente(optPaciente.get());
        agenda.setDataCriacao(LocalDateTime.now());

        return repository.save(agenda);
    }
}
