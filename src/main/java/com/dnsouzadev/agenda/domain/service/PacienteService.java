package com.dnsouzadev.agenda.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnsouzadev.agenda.domain.entity.Paciente;
import com.dnsouzadev.agenda.domain.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(Paciente paciente) {
        // Perform the necessary validations
        if (paciente.getNome() == null || paciente.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do paciente é obrigatório");
        }
        if (paciente.getSobrenome() == null || paciente.getSobrenome().isEmpty()) {
            throw new IllegalArgumentException("Sobrenome do paciente é obrigatório");
        }
        if (paciente.getCpf() == null || paciente.getCpf().isEmpty() || paciente.getCpf().length() != 11) {
            throw new IllegalArgumentException("CPF do paciente é obrigatório");
        }
        if (paciente.getEmail() == null || paciente.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email do paciente é obrigatório");
        }
        if (paciente.getCpf() != null && !paciente.getCpf().isEmpty()) {
            Optional<Paciente> pacienteExistente = repository.findByCpf(paciente.getCpf());
            if (pacienteExistente.isPresent() && !pacienteExistente.get().equals(paciente)) {
                throw new IllegalArgumentException("Já existe um paciente cadastrado com este CPF");
            }
        }
        // Add more validations if needed

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

}
