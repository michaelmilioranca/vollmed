package med.voll.api.repository.paciente;

import med.voll.api.controller.input.PacienteInput;
import med.voll.api.controller.output.PacienteCleanOutput;
import med.voll.api.controller.output.PacienteOutput;
import med.voll.api.repository.endereco.EnderecoTransformer;

public class PacienteTransformer {
    public static PacienteCleanOutput entityToOutRecord(Paciente paciente) {
        return PacienteCleanOutput.builder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .cpf(paciente.getCpf())
                .email(paciente.getEmail())
                .build();
    }

    public static Paciente inRecordToEntity(PacienteInput record) {
        return Paciente.builder()
                .nome(record.nome())
                .email(record.email())
                .cpf(record.cpf())
                .ativo(true)
                .telefone(record.telefone())
                .endereco(EnderecoTransformer.recordToEntity(record.endereco()))
                .build();
    }

    public static PacienteOutput entityToRecord(Paciente paciente) {
        return PacienteOutput.builder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .cpf(paciente.getCpf())
                .email(paciente.getEmail())
                .endereco(EnderecoTransformer.entityToRecord(paciente.getEndereco()))
                .build();
    }
}
