package med.voll.api.service.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record PacienteInput(
    @NotBlank(message = "{nome.obrigatorio}") String nome,
    @NotBlank(message = "{email.obrigatorio}") @Email String email,
    @NotBlank(message = "{telefone.obrigatorio}") String telefone,
    @NotBlank(message = "{cpf.obrigatorio}")
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}", message = "{cpf.invalido}")
        String cpf,
    @NotNull(message = "{endereco.obrigatorio}") @Valid EnderecoInput endereco) {}
