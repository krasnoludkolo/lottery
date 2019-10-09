package io.github.krasnoludkolo.game.api;

import java.util.UUID;

public final class BetDTO {

    public final UUID userId;
    public final int bet;

    public BetDTO(UUID userId, int bet) {
        this.userId = userId;
        this.bet = bet;
    }


}
