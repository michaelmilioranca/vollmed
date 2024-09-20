package med.voll.api.domain.consulta.validacoes;

import lombok.AllArgsConstructor;
import med.voll.api.controller.input.DadosAgendamentoConsultaInput;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.medico.Medico;
import med.voll.api.service.MedicoService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidarMedicoAtivo implements IValidarConsulta {
    final MedicoService medicoService;

    @Override
    public void validar(DadosAgendamentoConsultaInput input) {
        var idMedico = input.idMedico();
        var medico = medicoService.buscarPorId(idMedico);
        medico.ifPresent(ValidarMedicoAtivo::validarMedicoAtivo);
    }

    private static void validarMedicoAtivo(Medico medico) {
        if (!medico.getAtivo()) {
            throw new ValidacaoException("Medico inativo");
        }
    }
}
