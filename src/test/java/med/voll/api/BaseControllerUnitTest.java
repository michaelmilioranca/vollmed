package med.voll.api;

import med.voll.api.infra.security.TokenService;
import med.voll.api.repository.usario.UsuarioRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@ActiveProfiles("test")
public class BaseControllerUnitTest {
    @MockitoBean
    TokenService tokenService;

    @MockitoBean
    UsuarioRepository usuarioRepository;
}
