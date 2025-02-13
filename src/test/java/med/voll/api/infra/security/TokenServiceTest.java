package med.voll.api.infra.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.auth0.jwt.JWT;
import med.voll.api.repository.usario.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TokenService.class)
class TokenServiceTest {
    @Autowired
    private TokenService tokenService;

    @Test
    void deveGerarTokenCorretamente() {
        var userName = "cleiton_guerreiro";
        var usuario = Usuario.builder().username(userName).build();

        var token = tokenService.gerarToken(usuario);
        var jwt = JWT.decode(token);

        assertEquals(userName, tokenService.getSubject(token));
        assertEquals("API Voll.med", jwt.getIssuer());
    }
}
