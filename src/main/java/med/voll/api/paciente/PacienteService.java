package med.voll.api.paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService {

  @Autowired PacienteRepository repository;

  @Override
  public void save(InPacienteRecord record) {
    repository.save(PacienteTransformer.recordToEntity(record));
  }

  @Override
  public Page<OutPacienteRecord> findAll(Pageable paginacao) {
    return repository.findAll(paginacao).map(PacienteTransformer::entityToRecord);
  }
}
