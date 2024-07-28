package med.voll.api.util.validacoes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

public class ValidarControllerChain {

    public static DefinirMetodoHttpStep chamando(MockMvc mockMvc, String endpoint) {
        return new ValidarChain(mockMvc, endpoint);
    }

    public interface DefinirMetodoHttpStep {
        RetornaStatusStep comMetodoGet();

        DefineCorpoStep comMetodoPost();
    }

    public interface DefineCorpoStep {
        RetornaStatusStep comEntrada(Object entrada);
    }

    public interface RetornaStatusStep {
        RetornaSaidaStep retornaComStatus(HttpStatus status);
    }

    public interface RetornaSaidaStep {
        <T> void eSaida(Object saida, Class<T> tipoSaida);
    }

    private static class ValidarChain
            implements DefinirMetodoHttpStep, DefineCorpoStep, RetornaStatusStep, RetornaSaidaStep {
        private final MockMvc mockMvc;
        private final String endpoint;
        private final ObjectMapper objectMapper;
        private final ObjectWriter objectWriter;
        private MockHttpServletResponse response;
        private MockHttpServletRequestBuilder requestBuilder;

        public ValidarChain(MockMvc mockMvc, String endpoint) {
            this.mockMvc = mockMvc;
            this.endpoint = endpoint;
            this.objectMapper = new ObjectMapper();
            this.objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        }

        @Override
        public RetornaStatusStep comMetodoGet() {
            this.requestBuilder = get(endpoint);
            return this;
        }

        @Override
        public DefineCorpoStep comMetodoPost() {
            this.requestBuilder = post(endpoint);
            return this;
        }

        @Override
        public RetornaStatusStep comEntrada(Object entrada) {
            this.requestBuilder.contentType(MediaType.APPLICATION_JSON).content(converterParaJson(entrada));
            return this;
        }

        @Override
        public RetornaSaidaStep retornaComStatus(HttpStatus statusEsperado) {
            try {
                this.response = mockMvc.perform(requestBuilder)
                        .andDo(print())
                        .andReturn()
                        .getResponse();
                assertEquals(statusEsperado.value(), response.getStatus());
                return this;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao realizar a chamada REST", e);
            }
        }

        @Override
        public <T> void eSaida(Object saida, Class<T> tipoSaida) {
            try {
                var retornoChamada = response.getContentAsString(StandardCharsets.UTF_8);
                var saidaEmJson = converterSaida(saida, tipoSaida);
                assertEquals(saidaEmJson, retornoChamada);
            } catch (Exception e) {
                throw new RuntimeException(
                        "Houve um problema ao realizar a conversao do corpo enviado: %s"
                                .formatted(e.getLocalizedMessage()),
                        e);
            }
        }

        private String converterParaJson(Object converter) {
            try {
                return objectWriter.writeValueAsString(converter);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Erro ao converter corpo para JSON", e);
            }
        }

        private <T> Object converterSaida(Object saida, Class<T> tipoSaida) {
            return objectMapper.convertValue(saida, tipoSaida);
        }
    }
}
