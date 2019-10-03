package io.github.krasnoludkolo;

import io.github.krasnoludkolo.auth.AuthConfiguration;
import io.github.krasnoludkolo.auth.AuthFacade;
import io.github.krasnoludkolo.game.GameConfiguration;
import io.github.krasnoludkolo.points.PointConfiguration;
import io.github.krasnoludkolo.user.UserConfiguration;

final class App {


    void start() {
        PointConfiguration pointConfiguration = PointConfiguration.inMemory();
        UserConfiguration userConfiguration = UserConfiguration.inMemory(pointConfiguration.pointFacade);
        GameConfiguration gameConfiguration = GameConfiguration.inMemory(pointConfiguration.pointFacade, userConfiguration.userCheckers);
        AuthConfiguration authConfiguration = AuthConfiguration.inMemory();

        AuthFacade authFacade = authConfiguration.authFacade;

    }

}
