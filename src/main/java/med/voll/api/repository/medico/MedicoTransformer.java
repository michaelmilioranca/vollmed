package med.voll.api.repository.medico;

import med.voll.api.controller.input.MedicoInput;
import med.voll.api.controller.output.MedicoCleanOutput;
import med.voll.api.controller.output.MedicoOutput;
import med.voll.api.repository.endereco.EnderecoTransformer;

public class MedicoTransformer {

    public static Medico inRecordToEntity(final MedicoInput record) {
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

    public static MedicoCleanOutput entityToOutRecord(Medico medico) {
        return MedicoCleanOutput.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .email(medico.getEmail())
                .crm(medico.getCrm())
                .especialidade(medico.getEspecialidade())
                .build();
    }

    public static MedicoOutput entityToRecord(Medico medico) {
        return MedicoOutput.builder()
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
