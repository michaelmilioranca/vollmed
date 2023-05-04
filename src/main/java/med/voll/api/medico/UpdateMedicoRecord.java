package med.voll.api.medico;

import jakarta.validation.Valid;
import med.voll.api.endereco.EnderecoRecord;

public record UpdateMedicoRecord(String nome, String telefone, @Valid EnderecoRecord endereco) {}
