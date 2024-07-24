package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.service.input.EnderecoInput;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

  private String logradouro;
  private String bairro;
  private String cep;
  private String numero;
  private String complemento;
  private String cidade;
  private String uf;

  public void update(EnderecoInput updatedEndereco) {
    if (updatedEndereco.logradouro() != null) {
      this.logradouro = updatedEndereco.logradouro();
    }
    if (updatedEndereco.bairro() != null) {
      this.bairro = updatedEndereco.bairro();
    }
    if (updatedEndereco.cep() != null) {
      this.cep = updatedEndereco.cep();
    }
    if (updatedEndereco.numero() != null) {
      this.numero = updatedEndereco.numero();
    }
    if (updatedEndereco.complemento() != null) {
      this.complemento = updatedEndereco.complemento();
    }
    if (updatedEndereco.cidade() != null) {
      this.cidade = updatedEndereco.cidade();
    }
    if (updatedEndereco.uf() != null) {
      this.uf = updatedEndereco.uf();
    }
  }
}
