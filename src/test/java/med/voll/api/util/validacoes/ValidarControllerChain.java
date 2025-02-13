package med.voll.api.util.validacoes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ValidarControllerChain {

    public static DefinirMetodoHttpStep chamando(MockMvc mockMvc, String endpoint) {
        return new ValidarChain(mockMvc, endpoint);
    }

    public interface DefinirMetodoHttpStep {
        RetornaStatusStep comMetodoGet();

        DefineCorpoStep comMetodoPost();

        DefineCorpoStep comMetodoDelete();
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
        private MockHttpServletResponse response;
        private MockHttpServletRequestBuilder requestBuilder;

        public ValidarChain(MockMvc mockMvc, String endpoint) {
            this.mockMvc = mockMvc;
            this.endpoint = endpoint;
            this.objectMapper = new ObjectMapper()
                    .registerModule(new ParameterNamesModule())
                    .registerModule(new JavaTimeModule())
                    .enable(SerializationFeature.INDENT_OUTPUT);
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
        public DefineCorpoStep comMetodoDelete() {
            this.requestBuilder = delete(endpoint);
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
        public <T> void eSaida(Object objetoEsperado, Class<T> tipoSaida) {
            try {
                var corpoEmJson = response.getContentAsString(StandardCharsets.UTF_8);
                var corpoConvertido = converterSaida(corpoEmJson, tipoSaida);
                assertEquals(objetoEsperado, corpoConvertido);
            } catch (Exception e) {
                throw new RuntimeException(
                        "Houve um problema ao realizar a conversao do corpo enviado: %s"
                                .formatted(e.getLocalizedMessage()),
                        e);
            }
        }

        private String converterParaJson(Object converter) {
            try {
                return objectMapper.writeValueAsString(converter);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Erro ao converter corpo para JSON", e);
            }
        }

        private <T> Object converterSaida(String saida, Class<T> tipoSaida) throws JsonProcessingException {
            return objectMapper.readValue(saida, tipoSaida);
        }
    }
}
