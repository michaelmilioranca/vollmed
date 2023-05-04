package med.voll.api.paciente;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.endereco.Endereco;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
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

  public void update(UpdatePacienteRecord updatedPaciente) {
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
