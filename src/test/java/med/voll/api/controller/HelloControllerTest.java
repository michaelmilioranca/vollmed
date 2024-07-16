package med.voll.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import med.voll.api.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloController.class)
class HelloControllerTest extends BaseUnitTest {

  @Autowired private MockMvc mockMvc;

  @Test
  public void helloWorldTest() throws Exception {
    this.mockMvc.perform(get("/hello")).andExpect(status().isUnauthorized());
  }
}
