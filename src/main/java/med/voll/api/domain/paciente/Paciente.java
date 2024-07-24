package med.voll.api.domain.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.service.input.UpdatePacienteInput;

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
  private Boolean ativo;
  @Embedded private Endereco endereco;

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
