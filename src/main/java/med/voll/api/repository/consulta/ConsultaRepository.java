package med.voll.api.repository.consulta;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndData(long idMedico, LocalDateTime data);

    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime dataInicio, LocalDateTime dataFim);
}
