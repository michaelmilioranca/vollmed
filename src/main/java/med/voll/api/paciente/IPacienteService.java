package med.voll.api.paciente;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPacienteService {
  Paciente save(InPacienteRecord record);

  Page<OutPacienteRecord> findAllAtivo(Pageable paginacao);

  void inactive(Paciente paciente);

  Optional<Paciente> findById(Long id);

  void update(Paciente paciente, UpdatePacienteRecord updatedPaciente);
}
