package io.github.krasnoludkolo.auth;

import io.github.krasnoludkolo.infrastructure.Repository;
import io.vavr.control.Option;

final class Registration {

    private final Repository<AuthUser> repository;
    private final PasswordEncrypt passwordEncrypt;

    Registration(Repository<AuthUser> repository, PasswordEncrypt passwordEncrypt) {
        this.repository = repository;
        this.passwordEncrypt = passwordEncrypt;
    }

    AuthUser register(String password) {
        String encryptedPassword = passwordEncrypt.encryptPassword(password);
        return Option.of(AuthUser.create(encryptedPassword))
                .map(repository::save)
                .get();
    }
}
