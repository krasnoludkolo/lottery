package io.github.krasnoludkolo.user.api;

public final class UserDTO {

    private int id;
    private boolean isAdmin;

    public UserDTO(int id, boolean isAdmin) {
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
