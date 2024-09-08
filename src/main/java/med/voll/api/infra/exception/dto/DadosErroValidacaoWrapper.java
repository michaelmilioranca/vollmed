package med.voll.api.infra.exception.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record DadosErroValidacaoWrapper(List<DadosErroValidacao> dadosInvalidos) {}
