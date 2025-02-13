package med.voll.api.repository.consulta;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.controller.input.MotivoCancelamentoEnum;
import med.voll.api.repository.medico.Medico;
import med.voll.api.repository.paciente.Paciente;

@Entity
@Table(name = "consulta")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamentoEnum motivoCancelamento;

    public void cancelar(MotivoCancelamentoEnum motivo) {
        this.motivoCancelamento = motivo;
    }
}
