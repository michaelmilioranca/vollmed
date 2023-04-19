package med.voll.api.service;

import jakarta.transaction.Transactional;
import med.voll.api.medico.InMedicoRecord;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.medico.MedicoTransformer;
import med.voll.api.medico.OutMedicoRecord;
import med.voll.api.medico.UpdateMedicoRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService implements IMedicoService {

  @Autowired private MedicoRepository repository;

  @Transactional
  public void save(InMedicoRecord record) {
    repository.save(MedicoTransformer.recordToEntity(record));
  }

  @Override
  public Page<OutMedicoRecord> findAll(Pageable paginacao) {
    return repository.findAll(paginacao).map(MedicoTransformer::entityToRecord);
  }

  @Override
  public void update(UpdateMedicoRecord record) {
    repository.save(MedicoTransformer.updateRecordToEntity(record));
  }
}
