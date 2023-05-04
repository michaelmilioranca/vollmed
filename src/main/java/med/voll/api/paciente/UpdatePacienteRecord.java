package med.voll.api.paciente;

import jakarta.validation.Valid;
import med.voll.api.endereco.EnderecoRecord;

public record UpdatePacienteRecord(String nome, String telefone, @Valid EnderecoRecord endereco) {}
