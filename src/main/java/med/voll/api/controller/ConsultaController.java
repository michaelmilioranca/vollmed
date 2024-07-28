package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.controller.input.DadosAgendamentoConsultaInput;
import med.voll.api.controller.output.DadosDetalhamentoConsultaOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @PostMapping
    public ResponseEntity<DadosDetalhamentoConsultaOutput> agendar(
            @RequestBody @Valid DadosAgendamentoConsultaInput input) {
        return ResponseEntity.ok(DadosDetalhamentoConsultaOutput.builder().build());
    }
}
