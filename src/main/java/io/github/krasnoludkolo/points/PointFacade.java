package io.github.krasnoludkolo.points;

import io.github.krasnoludkolo.infrastructure.ActionError;
import io.github.krasnoludkolo.infrastructure.Repository;
import io.github.krasnoludkolo.resolver.Resolver;
import io.github.krasnoludkolo.resolver.Success;
import io.vavr.control.Either;

import java.util.UUID;

public class PointFacade {

    private final PointsService pointsService;
    private final PointCheckers pointCheckers;

    PointFacade(Repository<Point> repository, PointCheckers pointCheckers) {
        this.pointsService = new PointsService(repository);
        this.pointCheckers = pointCheckers;
    }

    public Either<ActionError, Success> addPointToUser(UUID userId) {
        return Resolver
                .when(
                        pointCheckers.userExists(userId)
                )
                .perform(
                        pointsService.addPointToUser(userId)
                );
    }

    public Either<ActionError, Integer> getUserPoints(UUID userId) {
        return Resolver
                .when(
                        pointCheckers.userExists(userId)
                )
                .perform(
                        pointsService.getUserPoints(userId)
                );
    }

    public Either<ActionError, UUID> createResultForUser(UUID id) {
        return Resolver
                .when(
                        pointCheckers.userNotExists(id)
                ).perform(
                        pointsService.createResultForUser(id)
                );
    }

}
