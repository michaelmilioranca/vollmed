package med.voll.api.controller.output;

import lombok.Builder;
import med.voll.api.repository.medico.EspecialidadeEnum;

@Builder
public record MedicoCleanOutput(Long id, String nome, String email, String crm, EspecialidadeEnum especialidade) {}
