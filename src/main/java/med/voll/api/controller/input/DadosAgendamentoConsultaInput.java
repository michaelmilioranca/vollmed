package med.voll.api.controller.input;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import med.voll.api.repository.medico.EspecialidadeEnum;

@Builder
public record DadosAgendamentoConsultaInput(
        Long idMedico,
        @NotNull Long idPaciente,
        EspecialidadeEnum especialidade,
        @NotNull @Future LocalDateTime data) {}
