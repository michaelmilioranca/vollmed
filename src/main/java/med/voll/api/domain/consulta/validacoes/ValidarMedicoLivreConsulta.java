package med.voll.api.domain.consulta.validacoes;

import java.util.Objects;
import lombok.AllArgsConstructor;
import med.voll.api.controller.input.DadosAgendamentoConsultaInput;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.consulta.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidarMedicoLivreConsulta implements IValidarConsulta {
    final ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosAgendamentoConsultaInput input) {
        if (Objects.nonNull(input.idMedico())) {
            var possuiConsultaMesmoHorario = consultaRepository.existsByMedicoIdAndData(input.idMedico(), input.data());
            if (possuiConsultaMesmoHorario) {
                throw new ValidacaoException("Medico ja possui consulta nesse horario");
            }
        }
    }
}
