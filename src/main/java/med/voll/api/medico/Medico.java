package med.voll.api.medico;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Entity
@Table(name = "medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Medico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String nome;
  private String telefone;
  private String email;
  private String crm;
  private Boolean ativo;

  @Enumerated(EnumType.STRING)
  private EspecialidadeEnum especialidade;
  // Basically this turns the fields as a separated object instead of having them all here
  @Embedded private Endereco endereco;

  public void update(UpdateMedicoRecord updatedMedico) {
    if (updatedMedico.nome() != null) {
      this.nome = updatedMedico.nome();
    }
    if (updatedMedico.telefone() != null) {
      this.telefone = updatedMedico.telefone();
    }
  }

  public void inactive() {
    this.ativo = false;
  }
}
