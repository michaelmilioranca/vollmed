package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.controller.input.MedicoInput;
import med.voll.api.controller.input.UpdateMedicoInput;
import med.voll.api.controller.output.MedicoCleanOutput;
import med.voll.api.controller.output.MedicoOutput;
import med.voll.api.repository.medico.MedicoTransformer;
import med.voll.api.service.IMedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final IMedicoService service;

    public MedicoController(final IMedicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MedicoOutput> save(
            @RequestBody @Valid MedicoInput medicoInput, UriComponentsBuilder uriBuilder) {
        var medico = service.save(medicoInput);
        var uri =
                uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(MedicoTransformer.entityToRecord(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoCleanOutput>> findAll(
            @PageableDefault(
                            size = 5,
                            sort = {"nome"})
                    Pageable paginacao) {
        return ResponseEntity.ok(service.findAllAtivo(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoOutput> findById(@PathVariable Long id) {
        return ResponseEntity.ok(MedicoTransformer.entityToRecord(service.findById(id)));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MedicoOutput> update(@PathVariable Long id, @RequestBody @Valid UpdateMedicoInput record) {
        var medico = service.findById(id);
        service.update(medico, record);
        return ResponseEntity.ok(MedicoTransformer.entityToRecord(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var medico = service.findById(id);
        service.inactive(medico);
        return ResponseEntity.noContent().build();
    }
}
