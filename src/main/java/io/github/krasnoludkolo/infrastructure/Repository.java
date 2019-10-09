package io.github.krasnoludkolo.infrastructure;

import io.github.krasnoludkolo.resolver.Success;
import io.vavr.collection.List;
import io.vavr.control.Option;

import java.io.Serializable;
import java.util.UUID;

public interface Repository<T extends Identifiable> extends Serializable {
    T save(T t);

    Option<T> findOne(UUID id);

    List<T> findAll();

    Success delete(UUID id);

    Success update(T t);
}
