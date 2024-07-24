package med.voll.api.service.output;

import lombok.Builder;

@Builder
public record PacienteCleanOutput(Long id, String nome, String email, String cpf) {}
