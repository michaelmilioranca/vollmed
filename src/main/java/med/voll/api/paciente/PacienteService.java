package med.voll.api.paciente;

import lombok.AllArgsConstructor;
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
  public void update(Paciente paciente, UpdatePacienteRecord updatedPaciente) {
    paciente.update(updatedPaciente);
  }
}
