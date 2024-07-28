package med.voll.api;

import med.voll.api.infra.security.TokenService;
import med.voll.api.repository.usario.UsuarioRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class BaseControllerUnitTest {
    @MockBean
    TokenService tokenService;

    @MockBean
    UsuarioRepository usuarioRepository;
}
