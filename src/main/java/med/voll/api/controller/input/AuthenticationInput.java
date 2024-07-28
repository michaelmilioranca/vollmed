package med.voll.api.controller.input;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationInput(@NotBlank String user, @NotBlank String password) {}
