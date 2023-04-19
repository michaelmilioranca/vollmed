package med.voll.api.paciente;

import med.voll.api.endereco.EnderecoTransformer;

public class PacienteTransformer {
  public static OutPacienteRecord entityToRecord(Paciente paciente) {
    return OutPacienteRecord.builder()
        .id(paciente.getId())
        .nome(paciente.getNome())
        .cpf(paciente.getCpf())
        .email(paciente.getEmail())
        .build();
  }

  public static Paciente recordToEntity(InPacienteRecord record) {
    return Paciente.builder()
        .nome(record.nome())
        .email(record.email())
        .cpf(record.cpf())
        .telefone(record.telefone())
        .endereco(EnderecoTransformer.recordToEntity(record.endereco()))
        .build();
  }
}
