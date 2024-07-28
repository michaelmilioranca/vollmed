package med.voll.api.service;

import med.voll.api.controller.input.PacienteInput;
import med.voll.api.controller.input.UpdatePacienteInput;
import med.voll.api.controller.output.PacienteCleanOutput;
import med.voll.api.repository.paciente.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPacienteService {
    Paciente save(PacienteInput record);

    Page<PacienteCleanOutput> findAllAtivo(Pageable paginacao);

    void inactive(Paciente paciente);

    Paciente findById(Long id);

    void update(Paciente paciente, UpdatePacienteInput updatedPaciente);
}
