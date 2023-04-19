package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.InMedicoRecord;
import med.voll.api.medico.OutMedicoRecord;
import med.voll.api.medico.UpdateMedicoRecord;
import med.voll.api.service.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

  @Autowired private IMedicoService service;

  @PostMapping
  public void save(@RequestBody @Valid InMedicoRecord record) {
    service.save(record);
  }

  @GetMapping
  public Page<OutMedicoRecord> findAll(
      @PageableDefault(
              size = 5,
              sort = {"nome"})
          Pageable paginacao) {
    return service.findAll(paginacao);
  }

  @PutMapping("/{id}")
  @Transactional
  public void update(@RequestBody @Valid UpdateMedicoRecord record) {
    service.update(record);
  }
}