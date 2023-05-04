package med.voll.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import med.voll.api.service.MedicoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(MedicoController.class)
@ActiveProfiles("test")
class MedicoControllerTest {

  @Autowired MockMvc mockMvc;

  @MockBean MedicoService service;

  @Test
  void shouldReturnSomething() throws Exception {

    when(service.findAllAtivo(any())).thenReturn(Page.empty());
    this.mockMvc
        .perform(get("/medicos"))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
