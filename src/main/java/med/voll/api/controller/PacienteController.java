package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.IPacienteService;
import med.voll.api.paciente.InPacienteRecord;
import med.voll.api.paciente.OutPacienteRecord;
import med.voll.api.paciente.PacienteRecord;
import med.voll.api.paciente.PacienteTransformer;
import med.voll.api.paciente.UpdatePacienteRecord;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired IPacienteService service;

  @PostMapping
  @Transactional
  public ResponseEntity<PacienteRecord> save(
      @RequestBody @Valid InPacienteRecord record, UriComponentsBuilder uriBuilder) {

    var paciente = service.save(record);
    var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
    return ResponseEntity.created(uri).body(PacienteTransformer.entityToRecord(paciente));
  }

  @GetMapping
  public ResponseEntity<Page<OutPacienteRecord>> findAll(
      @PageableDefault(
              size = 5,
              sort = {"nome"})
          Pageable paginacao) {
    return ResponseEntity.ok(service.findAllAtivo(paginacao));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PacienteRecord> findById(@PathVariable Long id) {
    var optionalPaciente = service.findById(id);
    return optionalPaciente
        .map(paciente -> ResponseEntity.ok(PacienteTransformer.entityToRecord(paciente)))
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<PacienteRecord> update(
      @PathVariable Long id, @RequestBody @Valid UpdatePacienteRecord record) {
    var optionalPaciente = service.findById(id);
    return optionalPaciente
        .map(
            paciente -> {
              service.update(paciente, record);
              return ResponseEntity.ok(PacienteTransformer.entityToRecord(paciente));
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity delete(@PathVariable Long id) {
    var optionalPaciente = service.findById(id);
    return optionalPaciente
        .map(
            paciente -> {
              service.inactive(paciente);
              return ResponseEntity.noContent().build();
            })
        .orElse(ResponseEntity.notFound().build());
  }
}
