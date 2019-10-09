package io.github.krasnoludkolo.game.api;

import java.util.Objects;
import java.util.UUID;

public final class EndGameRequestDTO {

    private UUID gameId;
    private UUID userId;

    public EndGameRequestDTO(UUID gameId, UUID userId) {
        this.gameId = gameId;
        this.userId = userId;
    }

    public EndGameRequestDTO() {
    }

    public UUID getGameId() {
        return this.gameId;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EndGameRequestDTO)) return false;
        final EndGameRequestDTO other = (EndGameRequestDTO) o;
        if (this.getGameId() != other.getGameId()) return false;
        if (this.getUserId() != other.getUserId()) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, userId);
    }

    public String toString() {
        return "EndGameRequestDTO(gameId=" + this.getGameId() + ", userId=" + this.getUserId() + ")";
    }
}
