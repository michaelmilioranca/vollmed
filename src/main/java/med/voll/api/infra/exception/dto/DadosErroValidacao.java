package med.voll.api.infra.exception.dto;

import lombok.Builder;
import org.springframework.validation.FieldError;

@Builder
public record DadosErroValidacao(String campo, String mensagem) {
    public DadosErroValidacao(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}
