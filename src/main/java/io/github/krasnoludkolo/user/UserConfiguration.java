package io.github.krasnoludkolo.user;

import io.github.krasnoludkolo.infrastructure.InMemoryRepository;
import io.github.krasnoludkolo.infrastructure.Repository;
import io.github.krasnoludkolo.points.PointFacade;

public final class UserConfiguration {

    public final UserFacade userFacade;
    public final UserCheckers userCheckers;

    public static UserConfiguration inMemory(PointFacade pointFacade) {
        Repository<User> repository = new InMemoryRepository<>();
        return new UserConfiguration(repository, pointFacade);
    }

    private UserConfiguration(Repository<User> repository, PointFacade pointFacade) {
        userCheckers = new UserCheckers(repository);
        userFacade = new UserFacade(userCheckers, repository, pointFacade);
    }

}
