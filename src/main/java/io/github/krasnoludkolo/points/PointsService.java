package io.github.krasnoludkolo.points;

import io.github.krasnoludkolo.infrastructure.Repository;
import io.github.krasnoludkolo.resolver.Action;
import io.github.krasnoludkolo.resolver.Success;
import io.vavr.control.Option;

import java.util.UUID;

final class PointsService {

    private Repository<Point> repository;

    PointsService(Repository<Point> repository) {
        this.repository = repository;
    }

    Action<Success> addPointToUser(UUID userId) {
        return () -> repository
                .findOne(userId)
                .map(Point::increase)
                .map(repository::update)
                .get();
    }

    Action<Success> subtractPointToUser(UUID userId) {
        return () -> repository
                .findOne(userId)
                .map(Point::decrease)
                .map(repository::update)
                .get();
    }

    Action<Success> setUserPoints(UUID userId, int points) {
        return () -> repository
                .findOne(userId)
                .map(point -> point.setCount(points))
                .map(repository::update)
                .get();
    }

    Action<Integer> getUserPoints(UUID userId) {
        return () -> repository
                .findOne(userId)
                .map(p -> p.points)
                .get();
    }

    Action<UUID> createResultForUser(UUID id) {
        return () -> Option.of(Point.create(id))
                .map(repository::save)
                .map(Point::getId)
                .get();

    }
}
