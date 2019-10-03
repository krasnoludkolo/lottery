package io.github.krasnoludkolo.auth;

import io.github.krasnoludkolo.infrastructure.InMemoryRepository;
import io.github.krasnoludkolo.infrastructure.Repository;

public final class AuthConfiguration {

    public final AuthFacade authFacade;
    public final AuthenticationCheckers authenticationCheckers;

    public static AuthConfiguration inMemory() {
        Repository<AuthUser> repository = new InMemoryRepository<>();
        return new AuthConfiguration(repository);
    }

    private AuthConfiguration(Repository<AuthUser> repository) {
        PasswordEncrypt passwordEncrypt = new PlainTextPasswordEncrypt();
        TokenGenerator tokenGenerator = new TokenGenerator();
        Registration registration = new Registration(repository, passwordEncrypt);
        this.authenticationCheckers = new AuthenticationCheckers(repository, passwordEncrypt, tokenGenerator);
        this.authFacade = new AuthFacade(authenticationCheckers, registration);
    }
}
