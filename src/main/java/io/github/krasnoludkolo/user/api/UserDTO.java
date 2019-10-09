package io.github.krasnoludkolo.user.api;

import java.util.UUID;

public final class UserDTO {

    private UUID id;
    private boolean isAdmin;

    public UserDTO(UUID id, boolean isAdmin) {
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public UUID getId() {
        return id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
