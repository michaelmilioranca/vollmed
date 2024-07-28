package med.voll.api.controller.input;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record DadosAgendamentoConsultaInput(
        @NotNull Long idMedico, @NotNull Long idPaciente, @NotNull @Future LocalDateTime data) {}
