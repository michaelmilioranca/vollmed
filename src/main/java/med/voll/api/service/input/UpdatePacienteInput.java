package med.voll.api.service.input;

import jakarta.validation.Valid;

public record UpdatePacienteInput(String nome, String telefone, @Valid EnderecoInput endereco) {}
