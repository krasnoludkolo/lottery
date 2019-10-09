package io.github.krasnoludkolo.game.api;

import io.vavr.collection.List;

import java.util.UUID;

public class GameDTO {

    private final UUID id;
    private final List<BetDTO> usersBet;
    private int maxNumber;

    public GameDTO(UUID id, List<BetDTO> usersBet, int maxNumber) {
        this.id = id;
        this.usersBet = usersBet;
        this.maxNumber = maxNumber;
    }

    public UUID getId() {
        return this.id;
    }

    public List<BetDTO> getUsersBet() {
        return this.usersBet;
    }

    public int getMaxNumber() {
        return this.maxNumber;
    }
}
