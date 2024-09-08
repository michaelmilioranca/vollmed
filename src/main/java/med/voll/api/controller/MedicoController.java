package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.controller.input.MedicoInput;
import med.voll.api.controller.input.UpdateMedicoInput;
import med.voll.api.controller.output.MedicoCleanOutput;
import med.voll.api.controller.output.MedicoOutput;
import med.voll.api.repository.medico.MedicoTransformer;
import med.voll.api.service.MedicoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(final MedicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MedicoOutput> save(
            @RequestBody @Valid MedicoInput medicoInput, UriComponentsBuilder uriBuilder) {
        var medico = service.salvar(medicoInput);
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
        return ResponseEntity.ok(service.buscarTodosAtivos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoOutput> findById(@PathVariable Long id) {
        var medico = service.buscarPorId(id);

        return medico.map(value -> ResponseEntity.ok(MedicoTransformer.entityToRecord(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MedicoOutput> update(@PathVariable Long id, @RequestBody @Valid UpdateMedicoInput record) {
        var medico = service.buscarPorId(id);

        if (medico.isPresent()) {
            service.atualizar(medico.get(), record);
            return ResponseEntity.ok(MedicoTransformer.entityToRecord(medico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        var medico = service.buscarPorId(id);

        if (medico.isPresent()) {
            service.inativar(medico.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
