package med.voll.api.domain.consulta.validacoes;

import static med.voll.api.util.AgendamentoUtil.agendamentoInputBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.consulta.ConsultaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidarMaisDeUmaConsultaMesmoDiaTest {
    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private ValidarMaisDeUmaConsultaMesmoDia validarMaisDeUmaConsultaMesmoDia;

    @Test
    void deveLancarExceptionQuandoPacientePossuiConsultaMesmoDia() {
        when(consultaRepository.existsByPacienteIdAndDataBetween(any(), any(), any()))
                .thenReturn(Boolean.TRUE);
        var input = agendamentoInputBuilder().build();

        var exception = assertThrows(ValidacaoException.class, () -> validarMaisDeUmaConsultaMesmoDia.validar(input));
        assertEquals("Paciente ja possui consulta nesse dia", exception.getMessage());
    }

    @Test
    void naoDeveLancarExceptionQuandoPacienteNaoPossuiConsultaMesmoDia() {
        when(consultaRepository.existsByPacienteIdAndDataBetween(any(), any(), any()))
                .thenReturn(Boolean.FALSE);
        var input = agendamentoInputBuilder().build();

        assertDoesNotThrow(() -> validarMaisDeUmaConsultaMesmoDia.validar(input));
    }
}
