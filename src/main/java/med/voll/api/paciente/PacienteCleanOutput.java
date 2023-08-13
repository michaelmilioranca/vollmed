package med.voll.api.paciente;

import lombok.Builder;

@Builder
public record PacienteCleanOutput(Long id, String nome, String email, String cpf) {}
