package med.voll.api.domain.consulta.validacoes;

import static med.voll.api.util.AgendamentoUtil.agendamentoInputBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import med.voll.api.repository.medico.Medico;
import med.voll.api.service.MedicoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidarMedicoAtivoTest {
    @Mock
    private MedicoService medicoService;

    @InjectMocks
    private ValidarMedicoAtivo validarMedicoAtivo;

    @Test
    void deveLancarExceptionQuandoMedicoInativo() {
        when(medicoService.buscarPorId(any()))
                .thenReturn(Optional.of(Medico.builder().ativo(Boolean.FALSE).build()));

        var input = agendamentoInputBuilder().build();

        var exception = assertThrows(RuntimeException.class, () -> validarMedicoAtivo.validar(input));
        assertEquals("Medico inativo", exception.getMessage());
    }

    @Test
    void naoDeveLancarExceptionQuandoMedicoAtivo() {
        when(medicoService.buscarPorId(any()))
                .thenReturn(Optional.of(Medico.builder().ativo(Boolean.TRUE).build()));

        var input = agendamentoInputBuilder().build();

        assertDoesNotThrow(() -> validarMedicoAtivo.validar(input));
    }

    @Test
    void naoDeveLancarExceptionQuandoMedicoNaoEncontrado() {
        when(medicoService.buscarPorId(any())).thenReturn(Optional.empty());

        var input = agendamentoInputBuilder().build();

        assertDoesNotThrow(() -> validarMedicoAtivo.validar(input));
    }
}
