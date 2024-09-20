package med.voll.api.domain.consulta.validacoes;

import med.voll.api.controller.input.DadosAgendamentoConsultaInput;

public interface IValidarConsulta {
    void validar(final DadosAgendamentoConsultaInput input);
}
