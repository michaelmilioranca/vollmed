package med.voll.api.controller;

import static med.voll.api.util.validacoes.ValidarControllerChain.chamando;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import med.voll.api.BaseControllerUnitTest;
import med.voll.api.controller.input.MedicoInput;
import med.voll.api.service.MedicoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MedicoController.class)
@AutoConfigureMockMvc(addFilters = false)
class MedicoControllerTest extends BaseControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MedicoService service;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public MedicoService medicoService() {
            return Mockito.mock(MedicoService.class);
        }
    }

    @Test
    void deveRetornar200QuandoChamarBuscaPorTodosMedicos() {
        when(service.buscarTodosAtivos(any())).thenReturn(Page.empty());
        chamando(mockMvc, "/medicos").comMetodoGet().retornaComStatus(HttpStatus.OK);
    }

    @Test
    void deveRetornar400QuandoEnviaDadosInvalidos() {
        chamando(mockMvc, "/medicos")
                .comMetodoPost()
                .comEntrada(MedicoInput.builder().build())
                .retornaComStatus(HttpStatus.BAD_REQUEST);
    }
}
