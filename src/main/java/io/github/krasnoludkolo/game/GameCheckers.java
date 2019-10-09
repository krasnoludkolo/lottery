package io.github.krasnoludkolo.game;

import io.github.krasnoludkolo.game.api.GameActionError;
import io.github.krasnoludkolo.infrastructure.ActionError;
import io.github.krasnoludkolo.infrastructure.Repository;
import io.github.krasnoludkolo.resolver.Condition;
import io.github.krasnoludkolo.resolver.Success;
import io.vavr.control.Either;

import java.util.UUID;

final class GameCheckers {

    private final Repository<Game> repository;


    GameCheckers(Repository<Game> repository) {
        this.repository = repository;
    }

    public Condition<ActionError> gameExists(UUID gameId) {
        return () -> repository
                .findOne(gameId)
                .toEither((ActionError) GameActionError.GAME_NOT_FOUND)
                .map(Success::new);
    }

    public Condition<ActionError> isMaxNumberValid(int maxNumber) {
        return () -> evaluateBoolean(maxNumber > 0, GameActionError.IMPOSSIBLE_BET);
    }

    public Condition<ActionError> isBetPossible(int betValue, UUID gameId) {
        return () -> repository
                .findOne(gameId)
                .map(game -> game.isBetPossible(betValue))
                .toEither((ActionError) GameActionError.GAME_NOT_FOUND)
                .flatMap(possible -> evaluateBoolean(possible, GameActionError.IMPOSSIBLE_BET));
    }

    public Condition<ActionError> canEndGame(UUID gameId, UUID userId) {
        return () -> repository
                .findOne(gameId)
                .map(game -> game.canEndGame(userId))
                .toEither((ActionError) GameActionError.GAME_NOT_FOUND)
                .flatMap(possible -> evaluateBoolean(possible, GameActionError.USER_IS_NOT_GAME_ADMIN));
    }

    private Either<ActionError, Success> evaluateBoolean(boolean possible, GameActionError falseValue) {
        return possible ?
                Either.right(new Success())
                :
                Either.left(falseValue);
    }
}
