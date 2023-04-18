package med.voll.api.medico;

import med.voll.api.endereco.EnderecoTransformer;

public class MedicoTransformer {

  public static Medico recordToEntity(final MedicoRecord record) {
    return Medico.builder()
        .nome(record.nome())
        .email(record.email())
        .crm(record.crm())
        .especialidade(record.especialidade())
        .endereco(EnderecoTransformer.recordToEntity(record.endereco()))
        .build();
  }

  public static MedicoRecord entityToRecord(Medico medico) {
    return new MedicoRecord(
        medico.getNome(),
        medico.getEmail(),
        medico.getCrm(),
        medico.getEspecialidade(),
        EnderecoTransformer.entityToRecord(medico.getEndereco()));
  }
}
