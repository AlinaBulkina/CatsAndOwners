package models;

import java.util.Arrays;

public enum Breed {
    Munchkin,
    Oriental,
    Persian,
    Ragdoll,
    Sphynx,
    Unknown;

    public static Breed findByValue(String name) {
        for (Breed b : Breed.values()) {
            if (b.name().equals(name)) {
                return b;
            }
        }
        return Unknown;
    }
}
