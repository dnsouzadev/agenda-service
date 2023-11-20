package com.dnsouzadev.agenda.api.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.dnsouzadev.agenda.api.request.PacienteRequest;
import com.dnsouzadev.agenda.api.response.PacienteCompletoResponse;
import com.dnsouzadev.agenda.api.response.PacienteResponse;
import com.dnsouzadev.agenda.domain.entity.Paciente;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final ModelMapper mapper;

    public Paciente toPaciente(PacienteRequest request) {
        return mapper.map(request, Paciente.class);
    }

    public PacienteResponse toPacienteResponse(Paciente paciente) {
        return mapper.map(paciente, PacienteResponse.class);
    }

    public PacienteCompletoResponse toPacienteCompletoResponse(Paciente paciente) {
        return mapper.map(paciente, PacienteCompletoResponse.class);
    }


    public List<PacienteResponse> toPacienteResponseList(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(this::toPacienteResponse)
                .collect(Collectors.toList());
    }
}
