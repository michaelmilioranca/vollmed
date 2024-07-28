package med.voll.api.service;

import med.voll.api.controller.input.MedicoInput;
import med.voll.api.controller.input.UpdateMedicoInput;
import med.voll.api.controller.output.MedicoCleanOutput;
import med.voll.api.repository.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMedicoService {
    Medico save(MedicoInput record);

    Page<MedicoCleanOutput> findAllAtivo(Pageable paginacao);

    void update(Medico medico, UpdateMedicoInput toBeUpdate);

    Medico findById(Long id);

    void inactive(Medico medico);
}
