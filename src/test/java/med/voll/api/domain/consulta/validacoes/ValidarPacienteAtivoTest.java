package med.voll.api.domain.consulta.validacoes;

import static med.voll.api.util.AgendamentoUtil.agendamentoInputBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import med.voll.api.repository.paciente.Paciente;
import med.voll.api.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidarPacienteAtivoTest {
    @Mock
    private PacienteService pacienteService;

    @InjectMocks
    private ValidarPacienteAtivo validarPacienteAtivo;

    @Test
    void deveLancarExceptionQuandoMedicoInativo() {
        when(pacienteService.buscarPorId(any()))
                .thenReturn(Optional.of(Paciente.builder().ativo(Boolean.FALSE).build()));

        var input = agendamentoInputBuilder().build();

        var exception = assertThrows(RuntimeException.class, () -> validarPacienteAtivo.validar(input));
        assertEquals("Paciente inativo", exception.getMessage());
    }

    @Test
    void naoDeveLancarExceptionQuandoMedicoAtivo() {
        when(pacienteService.buscarPorId(any()))
                .thenReturn(Optional.of(Paciente.builder().ativo(Boolean.TRUE).build()));

        var input = agendamentoInputBuilder().build();

        assertDoesNotThrow(() -> validarPacienteAtivo.validar(input));
    }

    @Test
    void naoDeveLancarExceptionQuandoMedicoNaoEncontrado() {
        when(pacienteService.buscarPorId(any())).thenReturn(Optional.empty());

        var input = agendamentoInputBuilder().build();

        assertDoesNotThrow(() -> validarPacienteAtivo.validar(input));
    }
}
