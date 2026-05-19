package fr.origisoft.ecsspringboot.domain.model;

import java.util.UUID;

public record PostId(UUID id) {
    public static PostId generate() {
        return new PostId(UUID.randomUUID());
    }

    public static PostId of(UUID id) {
        return new PostId(id);
    }
}
