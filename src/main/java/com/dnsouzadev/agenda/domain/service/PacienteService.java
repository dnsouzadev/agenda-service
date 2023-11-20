package com.dnsouzadev.agenda.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnsouzadev.agenda.domain.entity.Paciente;
import com.dnsouzadev.agenda.domain.exception.BusinessException;
import com.dnsouzadev.agenda.domain.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;
import java.util.concurrent.atomic.AtomicBoolean;
@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {

        AtomicBoolean existCpf = new AtomicBoolean(false);

        repository.findByCpf(paciente.getCpf()).ifPresent(p -> {
            if (!p.getId().equals(paciente.getId())) {
                existCpf.set(true);
            }
        });

        if (existCpf.get() && paciente.getId() == null) {
            throw new BusinessException("Já existe um paciente cadastrado com este CPF.");

        }

        return repository.save(paciente);
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Paciente alterar(Long id, Paciente paciente) {
        Optional<Paciente> optPaciente = this.buscarPorId(id);

        if (optPaciente.isEmpty()) {
            throw new BusinessException("Paciente não cadastrado!");
        }

        paciente.setId(id);

        return salvar(paciente);
    }

}
