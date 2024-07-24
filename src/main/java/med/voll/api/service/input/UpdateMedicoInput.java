package med.voll.api.service.input;

import jakarta.validation.Valid;

public record UpdateMedicoInput(String nome, String telefone, @Valid EnderecoInput endereco) {}
