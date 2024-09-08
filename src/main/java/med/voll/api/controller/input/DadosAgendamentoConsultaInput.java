package med.voll.api.controller.input;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import med.voll.api.repository.medico.EspecialidadeEnum;

public record DadosAgendamentoConsultaInput(
        Long idMedico,
        @NotNull Long idPaciente,
        EspecialidadeEnum especialidade,
        @NotNull @Future LocalDateTime data) {}
