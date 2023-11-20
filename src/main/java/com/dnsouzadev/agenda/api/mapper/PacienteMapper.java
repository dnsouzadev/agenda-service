package com.dnsouzadev.agenda.api.mapper;

import com.dnsouzadev.agenda.api.request.PacienteRequest;
import com.dnsouzadev.agenda.api.response.PacienteResponse;
import com.dnsouzadev.agenda.domain.entity.Paciente;

public class PacienteMapper {

    // Private constructor to prevent instantiation
    private PacienteMapper() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Paciente toPaciente(PacienteRequest pacienteRequest) {
        Paciente paciente = new Paciente();
        paciente.setNome(pacienteRequest.getNome());
        paciente.setSobrenome(pacienteRequest.getSobrenome());
        paciente.setEmail(pacienteRequest.getEmail());
        paciente.setCpf(pacienteRequest.getCpf());
        return paciente;
    }

    public static PacienteResponse toPacienteResponse(Paciente paciente) {
        PacienteResponse pacienteResponse = new PacienteResponse();
        pacienteResponse.setId(paciente.getId());
        pacienteResponse.setNome(paciente.getNome());
        pacienteResponse.setSobrenome(paciente.getSobrenome());
        pacienteResponse.setEmail(paciente.getEmail());
        pacienteResponse.setCpf(paciente.getCpf());
        return pacienteResponse;
    }
}
