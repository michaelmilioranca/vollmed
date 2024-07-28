package med.voll.api.controller.input;

import jakarta.validation.Valid;

public record UpdatePacienteInput(String nome, String telefone, @Valid EnderecoInput endereco) {}
