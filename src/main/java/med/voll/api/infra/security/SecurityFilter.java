package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import med.voll.api.domain.usario.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

  private static final String PREFIXO_BEARER = "Bearer ";

  private final TokenService tokenService;
  private final UsuarioRepository usuarioRepository;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var token = extrairToken(request);

    token.ifPresent(this::validarToken);

    filterChain.doFilter(request, response);
  }

  private void validarToken(String token) {
    var subject = tokenService.getSubject(token);
    var usuario = usuarioRepository.findByUsername(subject);
    if (usuario != null) {
      var authentication =
          new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
  }

  private Optional<String> extrairToken(HttpServletRequest request) {
    var token = request.getHeader("Authorization");
    return token != null ? Optional.of(token.substring(PREFIXO_BEARER.length())) : Optional.empty();
  }
}
