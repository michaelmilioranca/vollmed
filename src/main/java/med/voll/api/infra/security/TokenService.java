package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import med.voll.api.domain.usario.Usuario;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  public String gerarToken(final Usuario usuario) {
    final var algorithm = Algorithm.HMAC256("donotchangeit");
    return JWT.create()
        .withIssuer("API Voll.med") // Identificação da aplicação
        .withSubject(usuario.getUsername())
        .withExpiresAt(obterDataExpiracao())
        .sign(algorithm);
  }

  private Instant obterDataExpiracao() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); // Fuso do BR
  }
}
