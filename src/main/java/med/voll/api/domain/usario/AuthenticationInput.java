package med.voll.api.domain.usario;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationInput(@NotBlank String user, @NotBlank String password) {}
