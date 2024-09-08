package med.voll.api.service;

import java.util.Optional;
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
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente salvar(PacienteInput record) {
        return repository.save(PacienteTransformer.inRecordToEntity(record));
    }

    public Page<PacienteCleanOutput> buscarTodosAtivos(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(PacienteTransformer::entityToOutRecord);
    }

    public void inativar(Paciente paciente) {
        paciente.inactive();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void atualizar(Paciente paciente, UpdatePacienteInput updatedPaciente) {
        paciente.update(updatedPaciente);
    }
}
