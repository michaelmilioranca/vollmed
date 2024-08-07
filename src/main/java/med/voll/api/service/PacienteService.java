package med.voll.api.service;

import lombok.AllArgsConstructor;
import med.voll.api.controller.input.PacienteInput;
import med.voll.api.controller.input.UpdatePacienteInput;
import med.voll.api.controller.output.PacienteCleanOutput;
import med.voll.api.repository.paciente.Paciente;
import med.voll.api.repository.paciente.PacienteRepository;
import med.voll.api.repository.paciente.PacienteTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PacienteService implements IPacienteService {

    private final PacienteRepository repository;

    @Override
    public Paciente save(PacienteInput record) {
        return repository.save(PacienteTransformer.inRecordToEntity(record));
    }

    @Override
    public Page<PacienteCleanOutput> findAllAtivo(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(PacienteTransformer::entityToOutRecord);
    }

    @Override
    public void inactive(Paciente paciente) {
        paciente.inactive();
    }

    @Override
    public Paciente findById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void update(Paciente paciente, UpdatePacienteInput updatedPaciente) {
        paciente.update(updatedPaciente);
    }
}
