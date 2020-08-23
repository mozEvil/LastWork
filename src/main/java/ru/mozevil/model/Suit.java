package ru.mozevil.model;

public enum Suit {
    CLUBS("c", "♣"),
    DIAMONDS("d", "♦"),
    HEARTS("h", "♥"),
    SPADES("s", "♠");

    private final String name;
    private final String icon;

    Suit(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return getIcon();
    }

    public static Suit getSuit(String name) {
        for (Suit s : values()) {
            if (s.getName().equals(name)) return s;
        }
        return null;
    }
}
