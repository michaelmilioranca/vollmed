package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usario.AuthenticationInput;
import med.voll.api.domain.usario.Usuario;
import med.voll.api.infra.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  public AutenticacaoController(
      final AuthenticationManager authenticationManager, final TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping
  public ResponseEntity login(@RequestBody @Valid AuthenticationInput authenticationInput) {
    var token =
        new UsernamePasswordAuthenticationToken(
            authenticationInput.user(), authenticationInput.password());
    var authentication = authenticationManager.authenticate(token);

    return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));
  }
}
