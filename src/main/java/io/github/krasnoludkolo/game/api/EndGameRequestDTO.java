package io.github.krasnoludkolo.game.api;

public final class EndGameRequestDTO {

    private int gameId;
    private int userId;

    public EndGameRequestDTO(int gameId, int userId) {
        this.gameId = gameId;
        this.userId = userId;
    }

    public EndGameRequestDTO() {
    }

    public int getGameId() {
        return this.gameId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setUserId(int userId) {
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

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getGameId();
        result = result * PRIME + this.getUserId();
        return result;
    }

    public String toString() {
        return "EndGameRequestDTO(gameId=" + this.getGameId() + ", userId=" + this.getUserId() + ")";
    }
}
