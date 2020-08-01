package ru.mozevil.model;

public enum Rank {

    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("T"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private final String name;

    Rank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Rank nextRank() {
        int firstIndex = 0;
        int lastIndex = values().length - 1;
        int nextIndex = this.ordinal() + 1;

        if (nextIndex > lastIndex) {
            return values()[firstIndex];
        }
        return values()[nextIndex];
    }

    public Rank previousRank() {
        int firstIndex = 0;
        int lastIndex = values().length - 1;
        int previousIndex = this.ordinal() - 1;

        if (previousIndex < firstIndex) {
            return values()[lastIndex];
        }
        return values()[previousIndex];
    }

    @Override
    public String toString() {
        return getName();
    }

    public static Rank getRank(String name) {
        for (Rank r : values()) {
            if (r.getName().equals(name)) return r;
        }
        return null;
    }
}
