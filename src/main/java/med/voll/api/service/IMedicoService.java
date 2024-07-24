package med.voll.api.service;

import med.voll.api.domain.medico.Medico;
import med.voll.api.service.input.MedicoInput;
import med.voll.api.service.input.UpdateMedicoInput;
import med.voll.api.service.output.MedicoCleanOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMedicoService {
  Medico save(MedicoInput record);

  Page<MedicoCleanOutput> findAllAtivo(Pageable paginacao);

  void update(Medico medico, UpdateMedicoInput toBeUpdate);

  Medico findById(Long id);

  void inactive(Medico medico);
}
