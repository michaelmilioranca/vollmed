package med.voll.api.medico;

import lombok.Builder;
import med.voll.api.endereco.EnderecoRecord;

@Builder
public record MedicoRecord(
    Long id,
    String nome,
    String email,
    String telefone,
    String crm,
    EspecialidadeEnum especialidade,
    EnderecoRecord endereco) {}
