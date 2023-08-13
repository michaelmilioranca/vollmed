package med.voll.api.service;

import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoCleanOutput;
import med.voll.api.medico.MedicoInput;
import med.voll.api.medico.UpdateMedicoRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMedicoService {
  Medico save(MedicoInput record);

  Page<MedicoCleanOutput> findAllAtivo(Pageable paginacao);

  void update(Medico medico, UpdateMedicoRecord toBeUpdate);

  Medico findById(Long id);

  void inactive(Medico medico);
}
