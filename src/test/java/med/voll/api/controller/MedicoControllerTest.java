package med.voll.api.controller;

import med.voll.api.BaseControllerUnitTest;
import med.voll.api.controller.input.MedicoInput;
import med.voll.api.service.MedicoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static med.voll.api.util.validacoes.ValidarControllerChain.chamando;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(MedicoController.class)
@AutoConfigureMockMvc(addFilters = false)
class MedicoControllerTest extends BaseControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MedicoService service;

    @Test
    void deveRetornar200QuandoChamarBuscaPorTodosMedicos() {
        when(service.findAllAtivo(any())).thenReturn(Page.empty());
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
