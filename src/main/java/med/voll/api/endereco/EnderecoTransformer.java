package med.voll.api.endereco;

public class EnderecoTransformer {

  public static Endereco recordToEntity(final EnderecoRecord record) {
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

  public static EnderecoRecord entityToRecord(Endereco endereco) {
    return new EnderecoRecord(
        endereco.getLogradouro(),
        endereco.getBairro(),
        endereco.getCep(),
        endereco.getCidade(),
        endereco.getUf(),
        endereco.getComplemento(),
        endereco.getNumero());
  }
}
