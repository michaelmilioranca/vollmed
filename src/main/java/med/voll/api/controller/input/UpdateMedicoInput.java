package med.voll.api.controller.input;

import jakarta.validation.Valid;

public record UpdateMedicoInput(String nome, String telefone, @Valid EnderecoInput endereco) {}
