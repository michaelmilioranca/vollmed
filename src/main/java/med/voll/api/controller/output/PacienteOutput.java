package med.voll.api.controller.output;

import lombok.Builder;
import med.voll.api.controller.input.EnderecoInput;

@Builder
public record PacienteOutput(Long id, String nome, String email, String telefone, String cpf, EnderecoInput endereco) {}
