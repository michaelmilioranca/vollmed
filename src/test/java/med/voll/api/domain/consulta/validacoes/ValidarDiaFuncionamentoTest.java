package med.voll.api.domain.consulta.validacoes;

import static med.voll.api.util.AgendamentoUtil.agendamentoInputBuilder;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import med.voll.api.infra.exception.ValidacaoException;
import org.junit.jupiter.api.Test;

public class ValidarDiaFuncionamentoTest {

    private final ValidarDiaFuncionamento validarDiaFuncionamento = new ValidarDiaFuncionamento();

    @Test
    void deveLancarExceptionQuandoDataInvalida() {
        var input = agendamentoInputBuilder()
                .data(LocalDateTime.now().with(DayOfWeek.SUNDAY))
                .build();

        var exception = assertThrows(ValidacaoException.class, () -> validarDiaFuncionamento.validar(input));
        assertEquals("Clinica aberta de segunda a sabado", exception.getMessage());
    }

    @Test
    void naoDeveLancarExceptionQuandoDataValida() {
        var input = agendamentoInputBuilder().build();

        assertDoesNotThrow(() -> validarDiaFuncionamento.validar(input));
    }
}
