package med.voll.api.controller.input;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record DadosCancelamentoConsulta(@NotNull Long idConsulta, @NotNull MotivoCancelamentoEnum motivoCancelamento) {}
