package med.voll.api.domain.consulta.validacoes;

import static med.voll.api.util.AgendamentoUtil.agendamentoInputBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.consulta.ConsultaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidarMedicoLivreConsultaTest {
    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private ValidarMedicoLivreConsulta validarMedicoLivreConsulta;

    @Test
    void deveLancarExceptionQuandoMedicoPossuiConsulta() {
        when(consultaRepository.existsByMedicoIdAndData(anyLong(), any())).thenReturn(Boolean.TRUE);

        var input = agendamentoInputBuilder().build();

        var exception = assertThrows(ValidacaoException.class, () -> validarMedicoLivreConsulta.validar(input));

        assertEquals("Medico ja possui consulta nesse horario", exception.getMessage());
    }

    @Test
    void naoDeveLancarExceptionQuandoMedicoNaoPossuiConsulta() {
        when(consultaRepository.existsByMedicoIdAndData(anyLong(), any())).thenReturn(Boolean.FALSE);

        var input = agendamentoInputBuilder().build();

        assertDoesNotThrow(() -> validarMedicoLivreConsulta.validar(input));
    }

    @Test
    void naoDeveLancarExceptionQuandoMedicoNaoEnviado() {
        var input = agendamentoInputBuilder().idMedico(null).build();

        assertDoesNotThrow(() -> validarMedicoLivreConsulta.validar(input));
    }
}
