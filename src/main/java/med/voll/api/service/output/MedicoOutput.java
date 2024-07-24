package med.voll.api.service.output;

import lombok.Builder;
import med.voll.api.domain.medico.EspecialidadeEnum;
import med.voll.api.service.input.EnderecoInput;

@Builder
public record MedicoOutput(
    Long id,
    String nome,
    String email,
    String telefone,
    String crm,
    EspecialidadeEnum especialidade,
    EnderecoInput endereco) {}
