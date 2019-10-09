package io.github.krasnoludkolo.game.api;

import java.util.UUID;

public final class NewBetDTO {

    public final UUID gameId;
    public final UUID userId;
    public final int bet;

    public NewBetDTO(UUID gameId, UUID userId, int bet) {
        this.gameId = gameId;
        this.userId = userId;
        this.bet = bet;
    }


}
