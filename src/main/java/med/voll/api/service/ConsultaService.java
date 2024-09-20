package med.voll.api.service;

import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import med.voll.api.controller.input.DadosAgendamentoConsultaInput;
import med.voll.api.controller.input.DadosCancelamentoConsulta;
import med.voll.api.controller.output.DadosDetalhamentoConsultaOutput;
import med.voll.api.domain.consulta.validacoes.IValidarConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.consulta.Consulta;
import med.voll.api.repository.consulta.ConsultaRepository;
import med.voll.api.repository.medico.Medico;
import med.voll.api.repository.medico.MedicoRepository;
import med.voll.api.repository.paciente.Paciente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final MedicoRepository medicoRepository;
    private final List<IValidarConsulta> validadoresDeConsultas;

    public DadosDetalhamentoConsultaOutput agendar(DadosAgendamentoConsultaInput consultaInput) {
        validarDadosEntrada(consultaInput);
        validadoresDeConsultas.forEach(validador -> validador.validar(consultaInput));
        var paciente = buscarPaciente(consultaInput);
        var medico = escolherMedico(consultaInput);
        var consulta = new Consulta(null, medico, paciente, consultaInput.data(), null);
        consultaRepository.save(consulta);
        return DadosDetalhamentoConsultaOutput.builder().build();
    }

    private void validarDadosEntrada(DadosAgendamentoConsultaInput consultaInput) {
        if (Objects.isNull(consultaInput.idMedico()) && Objects.isNull(consultaInput.especialidade())) {
            throw new ValidacaoException("Especialidade é obrigatória quando não selecionado um médico.");
        }
    }

    private Medico escolherMedico(DadosAgendamentoConsultaInput consultaInput) {
        if (!Objects.isNull(consultaInput.idMedico())) {
            return buscarMedicoPorId(consultaInput);
        }

        return medicoRepository
                .buscarMedicoAleatorioLivrePorEspecialidadeEData(consultaInput.especialidade(), consultaInput.data())
                .orElseThrow(() ->
                        new ValidacaoException("Não encontrado médico disponível para a especialidade %s e data %s"
                                .formatted(consultaInput.especialidade(), consultaInput.data())));
    }

    private Medico buscarMedicoPorId(DadosAgendamentoConsultaInput consultaInput) {
        return medicoService
                .buscarPorId(consultaInput.idMedico())
                .orElseThrow(() -> new ValidacaoException(
                        "Não foi localizado médico com ID: %s".formatted(consultaInput.idMedico())));
    }

    private Paciente buscarPaciente(DadosAgendamentoConsultaInput consultaInput) {
        return pacienteService
                .buscarPorId(consultaInput.idPaciente())
                .orElseThrow(() -> new ValidacaoException(
                        "Não foi localizado paciente com o ID: %s".formatted(consultaInput.idPaciente())));
    }

    @Transactional
    public void cancelar(DadosCancelamentoConsulta input) {
        var consulta = consultaRepository
                .findById(input.idConsulta())
                .orElseThrow(() -> new ValidacaoException("Consulta não encontrada."));
        consulta.cancelar(input.motivoCancelamento());
    }
}
