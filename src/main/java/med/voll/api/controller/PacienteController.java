package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.PacienteTransformer;
import med.voll.api.service.IPacienteService;
import med.voll.api.service.input.PacienteInput;
import med.voll.api.service.input.UpdatePacienteInput;
import med.voll.api.service.output.PacienteCleanOutput;
import med.voll.api.service.output.PacienteOutput;
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
@RequestMapping("/pacientes")
public class PacienteController {

  private final IPacienteService service;

  public PacienteController(final IPacienteService service) {
    this.service = service;
  }

  @PostMapping
  @Transactional
  public ResponseEntity<PacienteOutput> save(
      @RequestBody @Valid PacienteInput input, UriComponentsBuilder uriBuilder) {
    var paciente = service.save(input);
    var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
    return ResponseEntity.created(uri).body(PacienteTransformer.entityToRecord(paciente));
  }

  @GetMapping
  public ResponseEntity<Page<PacienteCleanOutput>> findAll(
      @PageableDefault(
              size = 5,
              sort = {"nome"})
          Pageable paginacao) {
    return ResponseEntity.ok(service.findAllAtivo(paginacao));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PacienteOutput> findById(@PathVariable Long id) {
    return ResponseEntity.ok(PacienteTransformer.entityToRecord(service.findById(id)));
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<PacienteOutput> update(
      @PathVariable Long id, @RequestBody @Valid UpdatePacienteInput record) {
    var paciente = service.findById(id);
    service.update(paciente, record);
    return ResponseEntity.ok(PacienteTransformer.entityToRecord(paciente));
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity delete(@PathVariable Long id) {
    var paciente = service.findById(id);
    service.inactive(paciente);
    return ResponseEntity.noContent().build();
  }
}
