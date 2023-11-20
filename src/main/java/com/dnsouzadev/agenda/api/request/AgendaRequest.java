package com.dnsouzadev.agenda.api.request;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequest {

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Horário é obrigatório")
    @FutureOrPresent(message = "Horário deve ser no futuro ou presente")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime horario;

    @NotNull(message = "Paciente é obrigatório")
    private Long pacienteId;

}
