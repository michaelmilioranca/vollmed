package med.voll.api.controller.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record EnderecoInput(
        @NotBlank(message = "Logradouro é obrigatório") String logradouro,
        @NotBlank(message = "Bairro é obrigatório") String bairro,
        @NotBlank(message = "CEP é obrigatório") @Pattern(regexp = "\\d{8}", message = "Formato inválido. Ex 12345678")
                String cep,
        @NotBlank(message = "Cidade é obrigatório") String cidade,
        @NotBlank(message = "UF é obrigatório") String uf,
        String complemento,
        String numero) {}
