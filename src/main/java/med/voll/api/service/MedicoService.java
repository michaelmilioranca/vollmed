package med.voll.api.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.MedicoTransformer;
import med.voll.api.service.input.MedicoInput;
import med.voll.api.service.input.UpdateMedicoInput;
import med.voll.api.service.output.MedicoCleanOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicoService implements IMedicoService {

  private final MedicoRepository medicoRepository;

  @Transactional
  public Medico save(MedicoInput record) {
    return medicoRepository.save(MedicoTransformer.inRecordToEntity(record));
  }

  @Override
  public Page<MedicoCleanOutput> findAllAtivo(Pageable paginacao) {
    return medicoRepository.findAllByAtivoTrue(paginacao).map(MedicoTransformer::entityToOutRecord);
  }

  @Override
  public void update(Medico medico, UpdateMedicoInput updatedMedico) {
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
