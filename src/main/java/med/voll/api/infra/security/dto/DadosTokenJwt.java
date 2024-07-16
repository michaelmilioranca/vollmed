package med.voll.api.infra.security.dto;

import lombok.Builder;

@Builder
public record DadosTokenJwt(String token) {}
