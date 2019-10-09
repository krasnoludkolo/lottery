package io.github.krasnoludkolo.game;

import io.github.krasnoludkolo.game.api.*;
import io.github.krasnoludkolo.infrastructure.ActionError;
import io.github.krasnoludkolo.points.PointConfiguration;
import io.github.krasnoludkolo.points.PointFacade;
import io.github.krasnoludkolo.user.UserCheckers;
import io.github.krasnoludkolo.user.UserConfiguration;
import io.github.krasnoludkolo.user.UserFacade;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameFacadeTest {

    private GameFacade gameFacade;
    private UserFacade userFacade;
    private final static int LOOSING_BET = 2;
    private final static int WINNING_BET = 5;

    @Before
    public void init() {
        PointFacade pointFacade = PointConfiguration.inMemory().pointFacade;
        UserConfiguration userConfiguration = UserConfiguration.inMemory(pointFacade);
        UserCheckers userCheckers = userConfiguration.userCheckers;
        userFacade = userConfiguration.userFacade;
        Random random = new Always4Random();
        gameFacade = GameConfiguration.inMemory(pointFacade, userCheckers, random).gameFacade;
    }


    @Test
    public void shouldCreateAndGetGame() {
        UUID creatorId = userFacade.createUser().getId();
        UUID id = gameFacade
                .createGame(10, creatorId)
                .get()
                .getId();
        Option<GameDTO> gameById = gameFacade.getGameById(id);
        assertTrue(gameById.isDefined());
    }

    @Test
    public void shouldNotGetNotExistingGame() {
        Option<GameDTO> gameById = gameFacade.getGameById(UUID.randomUUID());
        assertTrue(gameById.isEmpty());
    }

    @Test
    public void shouldGetAllCreatedGames() {
        UUID creatorId = userFacade.createUser().getId();
        gameFacade.createGame(1, creatorId);
        gameFacade.createGame(1, creatorId);
        gameFacade.createGame(1, creatorId);

        int size = gameFacade.getAllGames().size();
        assertEquals(3, size);
    }

    @Test
    public void shouldNewGameHasEmptyBetList() {
        UUID creatorId = userFacade.createUser().getId();
        UUID id = gameFacade.createGame(1, creatorId).get().getId();

        int size = gameFacade.getGameById(id).get().getUsersBet().size();

        assertEquals(0, size);
    }

    @Test
    public void shouldAddBet() {
        UUID creatorId = userFacade.createUser().getId();
        UUID userId = userFacade.createUser().getId();
        UUID gameId = gameFacade.createGame(2, creatorId).get().getId();

        gameFacade.addBet(new NewBetDTO(gameId, userId, LOOSING_BET));

        BetDTO betDTO = gameFacade.getGameById(gameId).get().getUsersBet().get(0);
        assertEquals(2, betDTO.bet);
        assertEquals(userId, betDTO.userId);
    }

    @Test
    public void shouldEndedGameReturningFinishGameDTO() {
        UUID creatorId = userFacade.createUser().getId();
        UUID id = gameFacade.createGame(10, creatorId).get().getId();

        gameFacade.endGame(new EndGameRequestDTO(id, creatorId));

        GameDTO gameDTO = gameFacade.getGameById(id).get();
        assertTrue(gameDTO instanceof FinishedGameDTO);
    }

    @Test
    public void shouldNotBeAbleToMakeImpossibleBet() {
        UUID creatorId = userFacade.createUser().getId();
        int maxNumber = 5;
        int impossibleBet = maxNumber + 1;
        UUID game = gameFacade.createGame(maxNumber, creatorId).get().getId();
        UUID user = userFacade.createUser().getId();

        ActionError error = gameFacade.addBet(new NewBetDTO(game, user, impossibleBet)).getLeft();

        assertEquals(GameActionError.IMPOSSIBLE_BET, error);
    }

    @Test
    public void shouldWinningNumberBeInResult() {
        UUID creatorId = userFacade.createUser().getId();
        UUID game = gameFacade.createGame(5, creatorId).get().getId();

        gameFacade.endGame(new EndGameRequestDTO(game, creatorId));

        FinishedGameDTO gameDTO = (FinishedGameDTO) gameFacade.getGameById(game).get();
        assertEquals(5, gameDTO.winnerNumber);
    }

    @Test
    public void shouldWinWithWinningBet() {
        UUID creatorId = userFacade.createUser().getId();
        UUID game = gameFacade.createGame(5, creatorId).get().getId();
        UUID user = userFacade.createUser().getId();

        gameFacade.addBet(new NewBetDTO(game, user, WINNING_BET));
        gameFacade.endGame(new EndGameRequestDTO(game, creatorId));

        FinishedGameDTO gameDTO = (FinishedGameDTO) gameFacade.getGameById(game).get();
        assertTrue(gameDTO.winnersId.contains(user));
    }

    @Test
    public void shouldSuperAdminBeAbleToEndGame() {
        UUID admin = userFacade.createAdminWithId(UUID.randomUUID()).getId();
        UUID creator = userFacade.createUser().getId();
        UUID game = gameFacade.createGame(1, creator).get().getId();

        Either<ActionError, GameDTO> endGame = gameFacade.endGame(new EndGameRequestDTO(game, admin));

        assertTrue(endGame.isRight());
    }

    @Test
    public void shouldNotNormalBeAbleToEndGame() {
        UUID normal = userFacade.createUser().getId();
        UUID creator = userFacade.createUser().getId();
        UUID game = gameFacade.createGame(1, creator).get().getId();

        ActionError error = gameFacade.endGame(new EndGameRequestDTO(game, normal)).getLeft();

        assertEquals(GameActionError.USER_IS_NOT_GAME_ADMIN, error);
    }

    @Test
    public void shouldCreatorBeAbleToEndGame() {
        UUID creator = userFacade.createUser().getId();
        UUID game = gameFacade.createGame(1, creator).get().getId();

        Either<ActionError, GameDTO> endGame = gameFacade.endGame(new EndGameRequestDTO(game, creator));

        assertTrue(endGame.isRight());
    }


}