package models;

public enum Color {
    White,
    Black,
    Grey,
    Fawn,
    Unknown;

    public static Color findByValue(String name) {
        for (Color b : Color.values()) {
            if (b.name().equals(name)) {
                return b;
            }
        }
        return Unknown;
    }

}
