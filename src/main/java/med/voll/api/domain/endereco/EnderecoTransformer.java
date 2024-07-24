package med.voll.api.domain.endereco;

import med.voll.api.service.input.EnderecoInput;

public class EnderecoTransformer {

  public static Endereco recordToEntity(final EnderecoInput record) {
    return Endereco.builder()
        .cep(record.cep())
        .uf(record.uf())
        .cidade(record.cidade())
        .bairro(record.bairro())
        .logradouro(record.logradouro())
        .numero(record.numero())
        .complemento(record.complemento())
        .build();
  }

  public static EnderecoInput entityToRecord(Endereco endereco) {
    return new EnderecoInput(
        endereco.getLogradouro(),
        endereco.getBairro(),
        endereco.getCep(),
        endereco.getCidade(),
        endereco.getUf(),
        endereco.getComplemento(),
        endereco.getNumero());
  }
}
