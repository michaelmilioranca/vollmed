package med.voll.api.domain.consulta.validacoes;

import lombok.AllArgsConstructor;
import med.voll.api.controller.input.DadosAgendamentoConsultaInput;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.consulta.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidarMaisDeUmaConsultaMesmoDia implements IValidarConsulta {
    private final ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosAgendamentoConsultaInput input) {
        var possuiConsultaMesmoDia = consultaRepository.existsByPacienteIdAndDataBetween(
                input.idPaciente(), input.data().withHour(7), input.data().withHour(18));
        if (possuiConsultaMesmoDia) {
            throw new ValidacaoException("Paciente ja possui consulta nesse dia");
        }
    }
}
