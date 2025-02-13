package med.voll.api.repository.medico;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.persistence.EntityManager;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import med.voll.api.repository.consulta.Consulta;
import med.voll.api.repository.endereco.Endereco;
import med.voll.api.repository.paciente.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MedicoRepository medicoRepository;

    @Test
    void deveVoltarNullQuandoNaoHouverMedicoParaAData() {
        var proximaSegundaAsDez =
                LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var medico = cadastrarMedico(dadosMedico().build());
        var paciente = cadastrarPaciente(dadosPaciente().build());
        cadastrarConsulta(medico, paciente, proximaSegundaAsDez);

        var medicoDisponivel = medicoRepository.buscarMedicoAleatorioLivrePorEspecialidadeEData(
                EspecialidadeEnum.CARDIOLOGIA, proximaSegundaAsDez);

        assertTrue(medicoDisponivel.isEmpty());
    }

    @Test
    void deveEncontrarMedicoQuandoHouverMedicoParaADataEEspecialidade() {
        var proximaSegundaAsDez =
                LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        cadastrarMedico(dadosMedico().build());

        var medicoDisponivel = medicoRepository.buscarMedicoAleatorioLivrePorEspecialidadeEData(
                EspecialidadeEnum.CARDIOLOGIA, proximaSegundaAsDez);

        assertFalse(medicoDisponivel.isEmpty());
    }

    @Test
    void deveRetornarEmptyQuandoNaoHouverMedicoParaADataEspecialidade() {
        var proximaSegundaAsDez =
                LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        cadastrarMedico(dadosMedico().build());

        var medicoDisponivel = medicoRepository.buscarMedicoAleatorioLivrePorEspecialidadeEData(
                EspecialidadeEnum.GINECOLOGIA, proximaSegundaAsDez);

        assertTrue(medicoDisponivel.isEmpty());
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(
                Consulta.builder().medico(medico).paciente(paciente).data(data).build());
    }

    private Medico cadastrarMedico(final Medico medico) {
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(final Paciente paciente) {
        em.persist(paciente);
        return paciente;
    }

    private Medico.MedicoBuilder dadosMedico() {
        return Medico.builder()
                .ativo(true)
                .email("medico.eficaz@faculdadedavida.com")
                .crm("123456")
                .nome("Estevao Arquiteto Das Indias")
                .especialidade(EspecialidadeEnum.CARDIOLOGIA)
                .endereco(dadosEndereco().build())
                .telefone("61999999999");
    }

    private Paciente.PacienteBuilder dadosPaciente() {
        return Paciente.builder()
                .ativo(true)
                .nome("Joao Alves Santana Livramento")
                .telefone("51998826864")
                .cpf("12345678910")
                .email("teste@testednv.com")
                .endereco(dadosEndereco().build());
    }

    private Endereco.EnderecoBuilder dadosEndereco() {
        return Endereco.builder()
                .bairro("bangladesh")
                .cep("00000000")
                .cidade("Sao Cristovao")
                .logradouro("rua do arroz seco")
                .uf("MT");
    }
}
