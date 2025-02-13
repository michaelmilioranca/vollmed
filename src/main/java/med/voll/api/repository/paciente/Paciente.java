package med.voll.api.repository.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import med.voll.api.controller.input.UpdatePacienteInput;
import med.voll.api.repository.endereco.Endereco;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paciente")
@Builder
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Builder.Default
    private Boolean ativo = false;

    @Embedded
    private Endereco endereco;

    public void inactive() {
        this.ativo = false;
    }

    public void update(UpdatePacienteInput updatedPaciente) {
        if (updatedPaciente.nome() != null) {
            this.nome = updatedPaciente.nome();
        }
        if (updatedPaciente.telefone() != null) {
            this.telefone = updatedPaciente.telefone();
        }
        if (updatedPaciente.endereco() != null) {
            this.endereco.update(updatedPaciente.endereco());
        }
    }
}
