package med.voll.api.medico;

import med.voll.api.endereco.EnderecoTransformer;

public class MedicoTransformer {

  public static Medico recordToEntity(final InMedicoRecord record) {
    return Medico.builder()
        .nome(record.nome())
        .email(record.email())
        .telefone(record.telefone())
        .crm(record.crm())
        .especialidade(record.especialidade())
        .endereco(EnderecoTransformer.recordToEntity(record.endereco()))
        .build();
  }

  public static OutMedicoRecord entityToRecord(Medico medico) {
    return OutMedicoRecord.builder()
        .id(medico.getId())
        .nome(medico.getNome())
        .email(medico.getEmail())
        .crm(medico.getCrm())
        .especialidade(medico.getEspecialidade())
        .build();
  }

  public static Medico updateRecordToEntity(UpdateMedicoRecord record) {
    return Medico.builder().build();
  }
}
