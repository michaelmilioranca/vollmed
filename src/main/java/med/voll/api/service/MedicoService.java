package med.voll.api.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import med.voll.api.medico.MedicoRecord;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.medico.MedicoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService implements IMedicoService {

  @Autowired private MedicoRepository repository;

  @Transactional
  public void save(MedicoRecord record) {
    repository.save(MedicoTransformer.recordToEntity(record));
  }

  @Override
  public List<MedicoRecord> findAll() {
    return repository.findAll().stream()
        .map(MedicoTransformer::entityToRecord)
        .collect(Collectors.toList());
  }
}
