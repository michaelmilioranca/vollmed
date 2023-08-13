package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.EnderecoRecord;

public record MedicoInput(
    @NotBlank(message = "{nome.obrigatorio}") String nome,
    @NotBlank(message = "{email.obrigatorio}")
        @Email(message = "Formato de email inválido. Ex: sample@example.com.br")
        String email,
    @NotBlank(message = "{telefone.obrigatorio}") String telefone,
    @NotBlank(message = "{crm.obrigatorio}")
        @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
        String crm,
    @NotNull(message = "{especialidade.obrigatorio}") EspecialidadeEnum especialidade,
    @NotNull(message = "{endereco.obrigatorio}") @Valid EnderecoRecord endereco) {}
