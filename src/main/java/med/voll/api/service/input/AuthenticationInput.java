package med.voll.api.service.input;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationInput(@NotBlank String user, @NotBlank String password) {}
