package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.IPacienteService;
import med.voll.api.paciente.InPacienteRecord;
import med.voll.api.paciente.OutPacienteRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

  @Autowired IPacienteService service;

  @PostMapping
  @Transactional
  public void save(@RequestBody @Valid InPacienteRecord record) {
    service.save(record);
  }

  @GetMapping
  public Page<OutPacienteRecord> findAll(
      @PageableDefault(
              size = 5,
              sort = {"nome"})
          Pageable paginacao) {
    return service.findAll(paginacao);
  }
}
