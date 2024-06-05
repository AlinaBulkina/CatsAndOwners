package models;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    USER,
    ADMIN;

    public static Optional<Role> findByValue(String name) {
        for (Role b : Role.values()) {
            if (b.name().equals(name)) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }
}
