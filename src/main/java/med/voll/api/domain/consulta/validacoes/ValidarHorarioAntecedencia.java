package med.voll.api.domain.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;
import med.voll.api.controller.input.DadosAgendamentoConsultaInput;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidarHorarioAntecedencia implements IValidarConsulta {

    @Override
    public void validar(DadosAgendamentoConsultaInput input) {
        if (Duration.between(LocalDateTime.now(), input.data()).toMinutes() < 30) {
            throw new ValidacaoException("A consulta deve ser agendada com antecedencia minima de 30 minutos");
        }
    }
}
