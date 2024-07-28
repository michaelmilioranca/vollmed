package med.voll.api.controller;

import static med.voll.api.util.validacoes.ValidarControllerChain.chamando;

import med.voll.api.BaseControllerUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = HelloController.class)
@AutoConfigureMockMvc(addFilters = false)
class HelloControllerTest extends BaseControllerUnitTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloWorldTest() {
        chamando(mockMvc, "/hello")
                .comMetodoGet()
                .retornaComStatus(HttpStatus.OK)
                .eSaida("Ola sou um tea bot", String.class);
    }
}
