package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import med.voll.api.controller.input.DadosAgendamentoConsultaInput;
import med.voll.api.controller.input.DadosCancelamentoConsulta;
import med.voll.api.controller.output.DadosDetalhamentoConsultaOutput;
import med.voll.api.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    private final ConsultaService service;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoConsultaOutput> agendar(
            @RequestBody @Valid DadosAgendamentoConsultaInput input) {
        return ResponseEntity.ok(service.agendar(input));
    }

    @DeleteMapping
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoConsulta input) {
        service.cancelar(input);
        return ResponseEntity.noContent().build();
    }
}
