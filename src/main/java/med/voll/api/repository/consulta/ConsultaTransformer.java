package med.voll.api.repository.consulta;

import med.voll.api.controller.output.DadosDetalhamentoConsultaOutput;

public class ConsultaTransformer {

    public static DadosDetalhamentoConsultaOutput consultaOutput(final Consulta consulta) {
        return DadosDetalhamentoConsultaOutput.builder()
                .id(consulta.getId())
                .idPaciente(consulta.getPaciente().getId())
                .idMedico(consulta.getMedico().getId())
                .data(consulta.getData())
                .build();
    }
}
