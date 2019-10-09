package io.github.krasnoludkolo.game.api;

import io.vavr.collection.List;

import java.util.UUID;

public class FinishedGameDTO extends GameDTO {

    public final List<UUID> winnersId;
    public final int winnerNumber;

    public FinishedGameDTO(UUID id, List<BetDTO> usersBet, int maxNumber, List<UUID> winnersId, int winnerNumber) {
        super(id, usersBet, maxNumber);
        this.winnersId = winnersId;
        this.winnerNumber = winnerNumber;
    }

}
