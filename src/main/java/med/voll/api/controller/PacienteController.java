package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.controller.input.PacienteInput;
import med.voll.api.controller.input.UpdatePacienteInput;
import med.voll.api.controller.output.PacienteCleanOutput;
import med.voll.api.controller.output.PacienteOutput;
import med.voll.api.repository.paciente.PacienteTransformer;
import med.voll.api.service.PacienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService service;

    public PacienteController(final PacienteService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PacienteOutput> save(
            @RequestBody @Valid PacienteInput input, UriComponentsBuilder uriBuilder) {
        var paciente = service.salvar(input);
        var uri = uriBuilder
                .path("/pacientes/{id}")
                .buildAndExpand(paciente.getId())
                .toUri();
        return ResponseEntity.created(uri).body(PacienteTransformer.entityToRecord(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteCleanOutput>> findAll(
            @PageableDefault(
                            size = 5,
                            sort = {"nome"})
                    Pageable paginacao) {
        return ResponseEntity.ok(service.buscarTodosAtivos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteOutput> findById(@PathVariable Long id) {
        var paciente = service.buscarPorId(id);
        return paciente.map(value -> ResponseEntity.ok(PacienteTransformer.entityToRecord(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PacienteOutput> update(
            @PathVariable Long id, @RequestBody @Valid UpdatePacienteInput record) {
        var paciente = service.buscarPorId(id);

        if (paciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.atualizar(paciente.get(), record);
        return ResponseEntity.ok(PacienteTransformer.entityToRecord(paciente.get()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var paciente = service.buscarPorId(id);

        if (paciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.inativar(paciente.get());
        return ResponseEntity.noContent().build();
    }
}
