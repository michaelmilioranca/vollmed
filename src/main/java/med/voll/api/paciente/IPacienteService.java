package med.voll.api.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPacienteService {
  Paciente save(PacienteInput record);

  Page<PacienteCleanOutput> findAllAtivo(Pageable paginacao);

  void inactive(Paciente paciente);

  Paciente findById(Long id);

  void update(Paciente paciente, UpdatePacienteRecord updatedPaciente);
}
