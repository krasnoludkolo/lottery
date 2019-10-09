package io.github.krasnoludkolo.auth;

import io.github.krasnoludkolo.auth.api.ApiToken;
import io.github.krasnoludkolo.infrastructure.ActionError;
import io.github.krasnoludkolo.points.PointConfiguration;
import io.github.krasnoludkolo.points.PointFacade;
import io.vavr.control.Either;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.assertTrue;

public class AuthFacadeTest {

    private static final String PASSWORD = "password";
    private AuthFacade authFacade;

    @Before
    public void init() {
        PointFacade pointFacade = PointConfiguration.inMemory().pointFacade;
        this.authFacade = AuthConfiguration.inMemory().authFacade;
    }

    @Test
    public void shouldRegisterAndLogin() {
        ApiToken register = authFacade.register(PASSWORD);
        UUID id = authFacade.getIdFromToken(register).get();

        Either<ActionError, ApiToken> loginResult = authFacade.login(id, PASSWORD);

        assertTrue(loginResult.isRight());
    }

}