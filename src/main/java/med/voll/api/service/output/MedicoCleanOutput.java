package med.voll.api.service.output;

import lombok.Builder;
import med.voll.api.domain.medico.EspecialidadeEnum;

@Builder
public record MedicoCleanOutput(
    Long id, String nome, String email, String crm, EspecialidadeEnum especialidade) {}
