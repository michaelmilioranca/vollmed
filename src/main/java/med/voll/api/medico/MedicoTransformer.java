package med.voll.api.medico;

import med.voll.api.endereco.EnderecoTransformer;

public class MedicoTransformer {

  public static Medico inRecordToEntity(final InMedicoRecord record) {
    return Medico.builder()
        .ativo(true)
        .nome(record.nome())
        .email(record.email())
        .telefone(record.telefone())
        .crm(record.crm())
        .especialidade(record.especialidade())
        .endereco(EnderecoTransformer.recordToEntity(record.endereco()))
        .build();
  }

  public static OutMedicoRecord entityToOutRecord(Medico medico) {
    return OutMedicoRecord.builder()
        .id(medico.getId())
        .nome(medico.getNome())
        .email(medico.getEmail())
        .crm(medico.getCrm())
        .especialidade(medico.getEspecialidade())
        .build();
  }

  public static MedicoRecord entityToRecord(Medico medico) {
    return MedicoRecord.builder()
        .id(medico.getId())
        .nome(medico.getNome())
        .email(medico.getEmail())
        .telefone(medico.getTelefone())
        .crm(medico.getCrm())
        .especialidade(medico.getEspecialidade())
        .endereco(EnderecoTransformer.entityToRecord(medico.getEndereco()))
        .build();
  }
}
