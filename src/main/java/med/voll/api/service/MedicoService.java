package med.voll.api.service;

import jakarta.transaction.Transactional;
import med.voll.api.medico.InMedicoRecord;
import med.voll.api.medico.Medico;
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

  @Autowired private MedicoRepository medicoRepository;

  @Transactional
  public Medico save(InMedicoRecord record) {
    return medicoRepository.save(MedicoTransformer.inRecordToEntity(record));
  }

  @Override
  public Page<OutMedicoRecord> findAllAtivo(Pageable paginacao) {
    return medicoRepository.findAllByAtivoTrue(paginacao).map(MedicoTransformer::entityToOutRecord);
  }

  @Override
  public void update(Medico medico, UpdateMedicoRecord updatedMedico) {
    medico.update(updatedMedico);
    if (updatedMedico.endereco() != null) {
      medico.getEndereco().update(updatedMedico.endereco());
    }
  }

  @Override
  public Medico findById(Long id) {
    return medicoRepository.getReferenceById(id);
  }

  @Override
  public void inactive(Medico medico) {
    medico.inactive();
  }
}
