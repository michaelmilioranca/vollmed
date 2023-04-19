package med.voll.api.service;

import med.voll.api.medico.InMedicoRecord;
import med.voll.api.medico.OutMedicoRecord;
import med.voll.api.medico.UpdateMedicoRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMedicoService {
  void save(InMedicoRecord record);

  Page<OutMedicoRecord> findAll(Pageable paginacao);

  void update(UpdateMedicoRecord record);
}
