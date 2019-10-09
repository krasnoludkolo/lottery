package io.github.krasnoludkolo.auth;

import io.github.krasnoludkolo.infrastructure.Identifiable;

import java.io.Serializable;
import java.util.UUID;

final class AuthUser implements Identifiable, Serializable {

    private final UUID id;
    final String password;

    static AuthUser create(String password) {
        UUID id = UUID.randomUUID();
        return new AuthUser(id, password);
    }

    private AuthUser(UUID id, String password) {
        this.id = id;
        this.password = password;
    }

    @Override
    public UUID getId() {
        return id;
    }
}
