package med.voll.api.service;

import static med.voll.api.controller.input.MotivoCancelamentoEnum.PACIENTE_DESISTIU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import med.voll.api.controller.input.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.validacoes.IValidarConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.consulta.Consulta;
import med.voll.api.repository.consulta.ConsultaRepository;
import med.voll.api.repository.medico.MedicoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {
    @Mock
    ConsultaRepository consultaRepository;

    @Mock
    MedicoService medicoService;

    @Mock
    PacienteService pacienteService;

    @Mock
    MedicoRepository medicoRepository;

    @Mock
    List<IValidarConsulta> validadoresDeConsultas;

    @InjectMocks
    ConsultaService consultaService;

    @Test
    void deveCancelarComSucessoQuandoEnontrarConsulta() {
        var consulta = Consulta.builder().id(1L).build();

        when(consultaRepository.findById(any())).thenReturn(Optional.of(consulta));

        consultaService.cancelar(dadosCancelamentoPadrao().build());

        verify(consultaRepository).findById(1L);
    }

    @Test
    void deveLancarExceptionQuandoNaoEncontrarConsulta() {
        when(consultaRepository.findById(any())).thenReturn(Optional.empty());

        var exception = assertThrows(
                ValidacaoException.class,
                () -> consultaService.cancelar(dadosCancelamentoPadrao().build()));

        assertEquals("Consulta não encontrada.", exception.getMessage());
    }

    private static DadosCancelamentoConsulta.DadosCancelamentoConsultaBuilder dadosCancelamentoPadrao() {
        return DadosCancelamentoConsulta.builder().idConsulta(1L).motivoCancelamento(PACIENTE_DESISTIU);
    }
}
