package med.voll.api.domain.consulta.validacoes;

import med.voll.api.controller.input.DadosAgendamentoConsultaInput;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidarHorarioFuncionamento implements IValidarConsulta {

    @Override
    public void validar(DadosAgendamentoConsultaInput input) {
        var hour = input.data().getHour();
        if (hour >= 7 && hour < 18) {
            throw new ValidacaoException("Consulta fora do horario de funcionamento");
        }
    }
}
