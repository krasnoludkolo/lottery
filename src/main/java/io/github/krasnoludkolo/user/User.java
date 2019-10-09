package io.github.krasnoludkolo.user;

import io.github.krasnoludkolo.infrastructure.Identifiable;
import io.github.krasnoludkolo.user.api.UserDTO;

import java.io.Serializable;
import java.util.UUID;

final class User implements Identifiable, Serializable {

    private static final long serialVersionUID = 1L;

    private final UUID id;
    final boolean isAdmin;

    static User createNormal() {
        UUID id = UUID.randomUUID();
        return new User(id, false);
    }

    static User createAdmin(UUID id) {
        return new User(id, true);
    }

    private User(UUID id, boolean isAdmin) {
        this.id = id;
        this.isAdmin = isAdmin;
    }

    User promoteToAdmin() {
        return new User(id, true);
    }

    UserDTO toDTO() {
        return new UserDTO(id, isAdmin);
    }

    @Override
    public UUID getId() {
        return id;
    }
}
