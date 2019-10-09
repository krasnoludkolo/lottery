package io.github.krasnoludkolo.infrastructure;

import java.io.Serializable;
import java.util.UUID;

public interface Identifiable extends Serializable {

    UUID getId();

}
