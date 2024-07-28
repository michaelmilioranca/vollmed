package med.voll.api.controller.output;

import lombok.Builder;
import med.voll.api.controller.input.EnderecoInput;
import med.voll.api.repository.medico.EspecialidadeEnum;

@Builder
public record MedicoOutput(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        EspecialidadeEnum especialidade,
        EnderecoInput endereco) {}
