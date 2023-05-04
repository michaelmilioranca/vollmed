package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.InMedicoRecord;
import med.voll.api.medico.MedicoRecord;
import med.voll.api.medico.MedicoTransformer;
import med.voll.api.medico.OutMedicoRecord;
import med.voll.api.medico.UpdateMedicoRecord;
import med.voll.api.service.IMedicoService;
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
@RequestMapping("/medicos")
public class MedicoController {

  @Autowired private IMedicoService service;

  @PostMapping
  public ResponseEntity<MedicoRecord> save(
      @RequestBody @Valid InMedicoRecord record, UriComponentsBuilder uriBuilder) {
    var medico = service.save(record);
    var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

    return ResponseEntity.created(uri).body(MedicoTransformer.entityToRecord(medico));
  }

  @GetMapping
  public ResponseEntity<Page<OutMedicoRecord>> findAll(
      @PageableDefault(
              size = 5,
              sort = {"nome"})
          Pageable paginacao) {
    return ResponseEntity.ok(service.findAllAtivo(paginacao));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MedicoRecord> findById(@PathVariable Long id) {
    var optionalMedico = service.findById(id);
    return optionalMedico
        .map(medico -> ResponseEntity.ok(MedicoTransformer.entityToRecord(medico)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<MedicoRecord> update(
      @PathVariable Long id, @RequestBody @Valid UpdateMedicoRecord record) {
    var optionalMedico = service.findById(id);
    return optionalMedico
        .map(
            medico -> {
              service.update(medico, record);
              return ResponseEntity.ok(MedicoTransformer.entityToRecord(medico));
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity delete(@PathVariable Long id) {
    var optionalMedico = service.findById(id);
    return optionalMedico
        .map(
            medico -> {
              service.inactive(optionalMedico.get());
              return ResponseEntity.noContent().build();
            })
        .orElse(ResponseEntity.notFound().build());
  }
}
