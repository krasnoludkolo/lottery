package io.github.krasnoludkolo.game.api;

import io.vavr.collection.List;

public class GameDTO {

    private final Integer id;
    private final List<BetDTO> usersBet;
    private int maxNumber;

    public GameDTO(Integer id, List<BetDTO> usersBet, int maxNumber) {
        this.id = id;
        this.usersBet = usersBet;
        this.maxNumber = maxNumber;
    }

    public Integer getId() {
        return this.id;
    }

    public List<BetDTO> getUsersBet() {
        return this.usersBet;
    }

    public int getMaxNumber() {
        return this.maxNumber;
    }
}
