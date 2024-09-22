package med.voll.api.domain.consulta.validacoes;

import static med.voll.api.util.AgendamentoUtil.agendamentoInputBuilder;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import med.voll.api.infra.exception.ValidacaoException;
import org.junit.jupiter.api.Test;

class ValidarHorarioAntecedenciaTest {

    private final ValidarHorarioAntecedencia validarHorarioAntecedencia = new ValidarHorarioAntecedencia();

    @Test
    void deveLancarExceptionQuandoAntecedenciaMaiorQue30() {
        var input = agendamentoInputBuilder()
                .data(LocalDateTime.now().minusMinutes(10))
                .build();

        var exception = assertThrows(ValidacaoException.class, () -> validarHorarioAntecedencia.validar(input));
        assertEquals("A consulta deve ser agendada com antecedencia minima de 30 minutos", exception.getMessage());
    }

    @Test
    void naoDeveLancarExceptionQuandoHoraValido() {
        var input = agendamentoInputBuilder()
                .data(LocalDateTime.now().plusMinutes(120))
                .build();

        assertDoesNotThrow(() -> validarHorarioAntecedencia.validar(input));
    }
}
