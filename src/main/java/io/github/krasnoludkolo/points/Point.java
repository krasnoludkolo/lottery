package io.github.krasnoludkolo.points;

import io.github.krasnoludkolo.infrastructure.Identifiable;

import java.io.Serializable;
import java.util.UUID;

final class Point implements Identifiable, Serializable {

    final int points;
    private final UUID userId;

    static Point create(UUID userId) {
        return new Point(0, userId);
    }

    private Point(int points, UUID userId) {
        this.points = points;
        this.userId = userId;
    }

    Point increase() {
        int newPoints = points + 1;
        return new Point(newPoints, userId);
    }

    Point decrease() {
        int newPoints = points - 1;
        return new Point(newPoints, userId);
    }

    Point setCount(int point) {
        return new Point(point, userId);
    }

    @Override
    public UUID getId() {
        return userId;
    }

}
