package med.voll.api.service.output;

import lombok.Builder;
import med.voll.api.service.input.EnderecoInput;

@Builder
public record PacienteOutput(
    Long id, String nome, String email, String telefone, String cpf, EnderecoInput endereco) {}
