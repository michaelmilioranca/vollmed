package med.voll.api.domain.consulta.validacoes;

import java.time.DayOfWeek;
import med.voll.api.controller.input.DadosAgendamentoConsultaInput;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidarDiaFuncionamento implements IValidarConsulta {
    @Override
    public void validar(DadosAgendamentoConsultaInput input) {
        if (DayOfWeek.SUNDAY.equals(input.data().getDayOfWeek())) {
            throw new ValidacaoException("Clinica aberta de segunda a sabado");
        }
    }
}
