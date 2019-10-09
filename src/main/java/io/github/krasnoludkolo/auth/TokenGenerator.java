package io.github.krasnoludkolo.auth;

import io.github.krasnoludkolo.auth.api.ApiToken;
import io.github.krasnoludkolo.auth.api.UserAuthError;
import io.github.krasnoludkolo.infrastructure.ActionError;
import io.github.krasnoludkolo.resolver.Action;
import io.github.krasnoludkolo.resolver.Success;
import io.vavr.control.Either;
import io.vavr.control.Try;

import java.util.UUID;

final class TokenGenerator {

    Action<ApiToken> generate(UUID id) {
        return () -> new ApiToken(id.toString());
    }

    Action<UUID> getIdFromToken(ApiToken token) {
        return () -> UUID.fromString(token.token);
    }

    public Either<ActionError, Success> isTokenValid(ApiToken token) {
        return Try.of(() -> UUID.fromString(token.token))
                .toEither()
                .map(Success::new)
                .mapLeft(x -> UserAuthError.INVALID_TOKEN);
    }
}
