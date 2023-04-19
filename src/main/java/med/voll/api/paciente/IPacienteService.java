package med.voll.api.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPacienteService {
  void save(InPacienteRecord record);

  Page<OutPacienteRecord> findAll(Pageable paginacao);
}
