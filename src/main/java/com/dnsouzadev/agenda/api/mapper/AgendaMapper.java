package com.dnsouzadev.agenda.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.dnsouzadev.agenda.api.request.AgendaRequest;
import com.dnsouzadev.agenda.api.response.AgendaResponse;
import com.dnsouzadev.agenda.domain.entity.Agenda;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AgendaMapper {

    private final ModelMapper mapper;

    public Agenda toAgenda(AgendaRequest request) {
        return mapper.map(request, Agenda.class);
    }

    public AgendaResponse toAgendaResponse(Agenda agenda) {
        return mapper.map(agenda, AgendaResponse.class);
    }

    public List<AgendaResponse> toAgendaResponseList(List<Agenda> agendas) {
        return agendas.stream()
                .map(this::toAgendaResponse)
                .toList();
    }
}
