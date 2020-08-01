package ru.mozevil.model;

public enum Suit {
    CLUBS("c"),
    DIAMONDS("d"),
    HEARTS("h"),
    SPADES("s");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public static Suit getSuit(String name) {
        for (Suit s : values()) {
            if (s.getName().equals(name)) return s;
        }
        return null;
    }
}
