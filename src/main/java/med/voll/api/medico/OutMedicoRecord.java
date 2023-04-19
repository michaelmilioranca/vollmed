package med.voll.api.medico;

import lombok.Builder;

@Builder
public record OutMedicoRecord(
    Long id, String nome, String email, String crm, EspecialidadeEnum especialidade) {}
