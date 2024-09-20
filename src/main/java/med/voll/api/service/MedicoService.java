package med.voll.api.service;

import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.AllArgsConstructor;
import med.voll.api.controller.input.MedicoInput;
import med.voll.api.controller.input.UpdateMedicoInput;
import med.voll.api.controller.output.MedicoCleanOutput;
import med.voll.api.repository.medico.Medico;
import med.voll.api.repository.medico.MedicoRepository;
import med.voll.api.repository.medico.MedicoTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    @Transactional
    public Medico salvar(MedicoInput record) {
        return medicoRepository.save(MedicoTransformer.inRecordToEntity(record));
    }

    public Page<MedicoCleanOutput> buscarTodosAtivos(Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao).map(MedicoTransformer::entityToOutRecord);
    }

    public void atualizar(Medico medico, UpdateMedicoInput dadosAtualizados) {
        medico.update(dadosAtualizados);
        if (dadosAtualizados.endereco() != null) {
            medico.getEndereco().update(dadosAtualizados.endereco());
        }
    }

    public Optional<Medico> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }

    public void inativar(Medico medico) {
        medico.inactive();
    }
}
