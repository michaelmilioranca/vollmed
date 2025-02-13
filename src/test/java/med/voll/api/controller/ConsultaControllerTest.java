package med.voll.api.controller;

import med.voll.api.BaseControllerUnitTest;
import med.voll.api.controller.input.DadosAgendamentoConsultaInput;
import med.voll.api.controller.input.DadosCancelamentoConsulta;
import med.voll.api.controller.input.MotivoCancelamentoEnum;
import med.voll.api.controller.output.DadosDetalhamentoConsultaOutput;
import med.voll.api.infra.exception.dto.DadosErroValidacao;
import med.voll.api.infra.exception.dto.DadosErroValidacaoWrapper;
import med.voll.api.repository.medico.EspecialidadeEnum;
import med.voll.api.service.ConsultaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static med.voll.api.util.validacoes.ValidarControllerChain.chamando;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(ConsultaController.class)
@AutoConfigureMockMvc(addFilters = false)
class ConsultaControllerTest extends BaseControllerUnitTest {
    private static final String NAO_DEVE_SER_NULO = "must not be null";

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ConsultaService service;

    @ParameterizedTest
    @MethodSource("carregarPayloadInvalidoAgendamento")
    void deveRetornarBadRequestQuandoDadosInvalidos(
            final DadosAgendamentoConsultaInput input, final DadosErroValidacaoWrapper body) {
        chamando(mockMvc, "/consulta")
                .comMetodoPost()
                .comEntrada(input)
                .retornaComStatus(HttpStatus.BAD_REQUEST)
                .eSaida(body, DadosErroValidacaoWrapper.class);
    }

    @Test
    void deveRetornarMedicoQuandoAgendaConsultaComSucesso() {
        final var dataHora = LocalDateTime.now().plusHours(1L);

        when(service.agendar(any())).thenReturn(dadosDetalhamentoOutputDefault(dataHora));

        chamando(mockMvc, "/consulta")
                .comMetodoPost()
                .comEntrada(dadosAgendamentoDefault().data(dataHora).build())
                .retornaComStatus(HttpStatus.OK)
                .eSaida(dadosDetalhamentoOutputDefault(dataHora), DadosDetalhamentoConsultaOutput.class);
    }

    @Test
    void deveRetornarNoContentQuandoCancelamentoComSucesso() {
        doNothing().when(service).cancelar(any());

        chamando(mockMvc, "/consulta")
                .comMetodoDelete()
                .comEntrada(DadosCancelamentoConsulta.builder()
                        .idConsulta(1L)
                        .motivoCancelamento(MotivoCancelamentoEnum.PACIENTE_DESISTIU)
                        .build())
                .retornaComStatus(HttpStatus.NO_CONTENT);
    }

    @ParameterizedTest
    @MethodSource("carregarPayloadInvalidoCancelamento")
    void deveRetornarBadRequestQuandoCancelamentoPossuirDadosInvalidos(
            final DadosCancelamentoConsulta input, final DadosErroValidacaoWrapper erroEsperado) {
        doNothing().when(service).cancelar(any());

        chamando(mockMvc, "/consulta")
                .comMetodoDelete()
                .comEntrada(input)
                .retornaComStatus(HttpStatus.BAD_REQUEST)
                .eSaida(erroEsperado, DadosErroValidacaoWrapper.class);
    }

    public static Stream<Arguments> carregarPayloadInvalidoAgendamento() {
        return Stream.of(
                Arguments.of(
                        DadosAgendamentoConsultaInput.builder().idPaciente(1L).build(),
                        construirSaidaParametrosObrigatorios(List.of(
                                dadosErroValidacaoDefault().campo("data").build()))),
                Arguments.of(
                        DadosAgendamentoConsultaInput.builder()
                                .data(LocalDateTime.now().plusHours(1L))
                                .build(),
                        construirSaidaParametrosObrigatorios(List.of(
                                dadosErroValidacaoDefault().campo("idPaciente").build()))),
                Arguments.of(
                        DadosAgendamentoConsultaInput.builder()
                                .idPaciente(1L)
                                .data(LocalDateTime.now())
                                .build(),
                        construirSaidaParametrosObrigatorios(List.of(dadosErroValidacaoDefault()
                                .campo("data")
                                .mensagem("must be a future date")
                                .build()))));
    }

    public static Stream<Arguments> carregarPayloadInvalidoCancelamento() {
        return Stream.of(
                Arguments.of(
                        DadosCancelamentoConsulta.builder().idConsulta(1L).build(),
                        construirSaidaParametrosObrigatorios(List.of(dadosErroValidacaoDefault()
                                .campo("motivoCancelamento")
                                .build()))),
                Arguments.of(
                        DadosCancelamentoConsulta.builder()
                                .motivoCancelamento(MotivoCancelamentoEnum.PACIENTE_DESISTIU)
                                .build(),
                        construirSaidaParametrosObrigatorios(List.of(
                                dadosErroValidacaoDefault().campo("idConsulta").build()))));
    }

    private static DadosDetalhamentoConsultaOutput dadosDetalhamentoOutputDefault(LocalDateTime dataHora) {
        return DadosDetalhamentoConsultaOutput.builder()
                .idMedico(5L)
                .idPaciente(2L)
                .data(dataHora)
                .build();
    }

    private static DadosAgendamentoConsultaInput.DadosAgendamentoConsultaInputBuilder dadosAgendamentoDefault() {
        return DadosAgendamentoConsultaInput.builder()
                .idMedico(5L)
                .idPaciente(2L)
                .especialidade(EspecialidadeEnum.ORTOPEDIA)
                .data(LocalDateTime.now().plusHours(1L));
    }

    private static DadosErroValidacaoWrapper construirSaidaParametrosObrigatorios(
            final List<DadosErroValidacao> dadosInvalidos) {
        return DadosErroValidacaoWrapper.builder()
                .dadosInvalidos(dadosInvalidos)
                .build();
    }

    private static DadosErroValidacao.DadosErroValidacaoBuilder dadosErroValidacaoDefault() {
        return DadosErroValidacao.builder().mensagem(NAO_DEVE_SER_NULO);
    }
}
