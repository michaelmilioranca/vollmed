package med.voll.api.service;

import java.util.List;
import med.voll.api.medico.MedicoRecord;

public interface IMedicoService {
  void save(MedicoRecord record);

  List<MedicoRecord> findAll();
}
