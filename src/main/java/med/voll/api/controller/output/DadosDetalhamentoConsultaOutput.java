package med.voll.api.controller.output;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record DadosDetalhamentoConsultaOutput(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {}
