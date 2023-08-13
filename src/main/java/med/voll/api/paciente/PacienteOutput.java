package med.voll.api.paciente;

import lombok.Builder;
import med.voll.api.endereco.EnderecoRecord;

@Builder
public record PacienteOutput(
    Long id, String nome, String email, String telefone, String cpf, EnderecoRecord endereco) {}
