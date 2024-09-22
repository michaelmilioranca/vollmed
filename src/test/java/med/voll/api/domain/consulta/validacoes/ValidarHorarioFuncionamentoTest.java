package med.voll.api.domain.consulta.validacoes;

import static med.voll.api.util.AgendamentoUtil.agendamentoInputBuilder;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import med.voll.api.infra.exception.ValidacaoException;
import org.junit.jupiter.api.Test;

class ValidarHorarioFuncionamentoTest {

    private final ValidarHorarioFuncionamento validarHorarioFuncionamento = new ValidarHorarioFuncionamento();

    @Test
    void deveLancarExceptionQuandoHoraMaiorQue18() {
        var input =
                agendamentoInputBuilder().data(LocalDateTime.now().withHour(19)).build();

        var exception = assertThrows(ValidacaoException.class, () -> validarHorarioFuncionamento.validar(input));
        assertEquals("Consulta fora do horario de funcionamento", exception.getMessage());
    }

    @Test
    void deveLancarExceptionQuandoHoraMenorQue7() {
        var input =
                agendamentoInputBuilder().data(LocalDateTime.now().withHour(6)).build();

        var exception = assertThrows(ValidacaoException.class, () -> validarHorarioFuncionamento.validar(input));
        assertEquals("Consulta fora do horario de funcionamento", exception.getMessage());
    }

    @Test
    void naoDeveLancarExceptionQuandoHoraValido() {
        var input = agendamentoInputBuilder().build();

        assertDoesNotThrow(() -> validarHorarioFuncionamento.validar(input));
    }
}
