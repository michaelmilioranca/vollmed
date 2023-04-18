package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.MedicoRecord;
import med.voll.api.service.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

  @Autowired private IMedicoService service;

  @PostMapping
  public void save(@RequestBody @Valid MedicoRecord record) {
    service.save(record);
  }

  @GetMapping
  public List<MedicoRecord> findAll() {
    return service.findAll();
  }
}
