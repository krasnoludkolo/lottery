package io.github.krasnoludkolo.game;

import io.github.krasnoludkolo.game.api.BetDTO;
import io.github.krasnoludkolo.game.api.NewBetDTO;

import java.util.UUID;

final class Bet {

    private UUID userId;
    private int bet;

    static Bet from(NewBetDTO dto) {
        return new Bet(dto.userId, dto.bet);
    }

    private Bet(UUID userId, int bet) {
        this.userId = userId;
        this.bet = bet;
    }

    boolean correct(int n) {
        return n == bet;
    }

    BetDTO toBetDTO() {
        return new BetDTO(userId, bet);
    }

}
