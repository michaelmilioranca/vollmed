package med.voll.api.service;

import med.voll.api.domain.paciente.Paciente;
import med.voll.api.service.input.PacienteInput;
import med.voll.api.service.input.UpdatePacienteInput;
import med.voll.api.service.output.PacienteCleanOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPacienteService {
  Paciente save(PacienteInput record);

  Page<PacienteCleanOutput> findAllAtivo(Pageable paginacao);

  void inactive(Paciente paciente);

  Paciente findById(Long id);

  void update(Paciente paciente, UpdatePacienteInput updatedPaciente);
}
