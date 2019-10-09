package io.github.krasnoludkolo.game;

import io.github.krasnoludkolo.game.api.BetDTO;
import io.github.krasnoludkolo.game.api.FinishedGameDTO;
import io.github.krasnoludkolo.game.api.GameDTO;
import io.github.krasnoludkolo.game.api.NewBetDTO;
import io.github.krasnoludkolo.infrastructure.Identifiable;
import io.github.krasnoludkolo.points.PointFacade;
import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

class Game implements Identifiable, Serializable {

    private final UUID id;
    private final int maxNumber;
    private final Map<UUID, Bet> bets;
    private final Random random;
    private final List<UUID> admins;

    static Game create(int maxNumber, Random random, UUID gameCreatorId) {
        UUID id = UUID.randomUUID();
        return new Game(id, maxNumber, HashMap.empty(), random, List.of(gameCreatorId));
    }

    private Game(UUID id, int maxNumber, Map<UUID, Bet> bets, Random random, List<UUID> adminsList) {
        this.id = id;
        this.maxNumber = maxNumber;
        this.bets = bets;
        this.random = random;
        this.admins = adminsList;
    }

    boolean isBetPossible(int bet) {
        return bet >= 0 && bet <= maxNumber;
    }

    GameDTO toDTO() {
        List<BetDTO> b = getBetsAsBetDTOList();
        return new GameDTO(id, b, maxNumber);
    }

    private List<BetDTO> getBetsAsBetDTOList() {
        return bets.values().map(Bet::toBetDTO).toList();
    }

    Game addBet(NewBetDTO newBet) {
        Bet bet = Bet.from(newBet);
        Map<UUID, Bet> newBetMap = bets.put(newBet.userId, bet);
        return new Game(id, maxNumber, newBetMap, random, admins);
    }

    FinishedGame endGame(PointFacade pointFacade) {
        return resolveThisGame();
    }

    private FinishedGame resolveThisGame() {
        int winningNumber = random.nextInt(maxNumber) + 1;
        List<UUID> winners = getWinners(winningNumber);
        return new FinishedGame(winners, winningNumber);
    }

    private List<UUID> getWinners(int winningNumber) {
        return bets
                .filterValues(bet -> bet.correct(winningNumber))
                .keySet()
                .toList();
    }

    @Override
    public UUID getId() {
        return id;
    }

    boolean canEndGame(UUID userId) {
        return admins.contains(userId);
    }

    class FinishedGame extends Game {

        private final List<UUID> winners;
        private final int winnerNumber;

        private FinishedGame(List<UUID> winners, int winnerNumber) {
            super(id, maxNumber, bets, random, admins);
            this.winners = winners;
            this.winnerNumber = winnerNumber;
        }

        @Override
        GameDTO toDTO() {
            List<BetDTO> b = getBetsAsBetDTOList();
            return new FinishedGameDTO(id, b, maxNumber, winners, winnerNumber);
        }

        @Override
        boolean isBetPossible(int bet) {
            return false;
        }

        @Override
        Game addBet(NewBetDTO bet) {
            return this;
        }

        @Override
        FinishedGame endGame(PointFacade pointFacade) {
            return this;
        }

        @Override
        boolean canEndGame(UUID userId) {
            return false;
        }
    }
}
