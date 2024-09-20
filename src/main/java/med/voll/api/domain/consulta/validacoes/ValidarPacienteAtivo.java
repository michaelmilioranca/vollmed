package med.voll.api.domain.consulta.validacoes;

import lombok.AllArgsConstructor;
import med.voll.api.controller.input.DadosAgendamentoConsultaInput;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.paciente.Paciente;
import med.voll.api.service.PacienteService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidarPacienteAtivo implements IValidarConsulta {

    final PacienteService pacienteService;

    @Override
    public void validar(DadosAgendamentoConsultaInput input) {
        var idPaciente = input.idPaciente();
        var paciente = pacienteService.buscarPorId(idPaciente);
        paciente.ifPresent(ValidarPacienteAtivo::validarPacienteAtivo);
    }

    private static void validarPacienteAtivo(Paciente paciente) {
        if (!paciente.getAtivo()) {
            throw new ValidacaoException("Paciente inativo");
        }
    }
}
