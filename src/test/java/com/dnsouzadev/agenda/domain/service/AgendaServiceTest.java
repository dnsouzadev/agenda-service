package com.dnsouzadev.agenda.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dnsouzadev.agenda.domain.entity.Agenda;
import com.dnsouzadev.agenda.domain.entity.Paciente;
import com.dnsouzadev.agenda.domain.exception.BusinessException;
import com.dnsouzadev.agenda.domain.repository.AgendaRepository;

@ExtendWith(MockitoExtension.class)
class AgendaServiceTest {

    @InjectMocks
    AgendaService service;

    @Mock
    PacienteService pacienteService;

    @Mock
    AgendaRepository repository;

    @Captor
    ArgumentCaptor<Agenda> agendaCaptor;

    @Test
    @DisplayName("Deve salvar angedamento com sucesso")
    void salvarComSucesso() {

        // arrange
        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Consulta");
        agenda.setHorario(now);

        Paciente paciente = new Paciente();
        paciente.setId(1L);

        agenda.setPaciente(paciente);

        Mockito.when(pacienteService.buscarPorId(agenda.getPaciente().getId())).thenReturn(Optional.of(paciente));
        Mockito.when(repository.findByHorario(now)).thenReturn(Optional.empty());


        // act
        service.save(agenda);


        // assertions
        Mockito.verify(pacienteService).buscarPorId(ArgumentMatchers.any());
        Mockito.verify(pacienteService).buscarPorId(agenda.getPaciente().getId());
        Mockito.verify(repository).findByHorario(now);

        Mockito.verify(repository).save(agendaCaptor.capture());
        Agenda agendaSalva = agendaCaptor.getValue();

        Assertions.assertThat(agendaSalva.getPaciente()).isNotNull();
        Assertions.assertThat(agendaSalva.getDataCriacao()).isNotNull();
    }

    @Test
    @DisplayName("Não deve salvar agendamento sem paciente")
    void salvarErroPacienteNaoEncontrado() {

        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Descricao do agendamento");
        agenda.setHorario(now);

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("Paciente");

        agenda.setPaciente(paciente);

        Mockito.when(pacienteService.buscarPorId(ArgumentMatchers.any())).thenReturn(Optional.empty());

        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            service.save(agenda);
        });

        Assertions.assertThat(businessException.getMessage()).isEqualTo("Paciente não encontrado");
    }
}
