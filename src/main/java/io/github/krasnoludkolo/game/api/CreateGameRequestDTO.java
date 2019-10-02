package io.github.krasnoludkolo.game.api;

public final class CreateGameRequestDTO {

    private int maxNumber;

    public CreateGameRequestDTO(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public CreateGameRequestDTO() {
    }

    public int getMaxNumber() {
        return this.maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateGameRequestDTO)) return false;
        final CreateGameRequestDTO other = (CreateGameRequestDTO) o;
        if (this.getMaxNumber() != other.getMaxNumber()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getMaxNumber();
        return result;
    }

    public String toString() {
        return "CreateGameRequestDTO(maxNumber=" + this.getMaxNumber() + ")";
    }
}
