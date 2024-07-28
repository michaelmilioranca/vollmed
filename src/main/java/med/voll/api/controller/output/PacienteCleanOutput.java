package med.voll.api.controller.output;

import lombok.Builder;

@Builder
public record PacienteCleanOutput(Long id, String nome, String email, String cpf) {}
