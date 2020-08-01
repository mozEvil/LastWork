package ru.mozevil.model;

public class Hand {

    private final Card card1;
    private final Card card2;

    public Hand(Card card1, Card card2) {
        this.card1 = card1;
        this.card2 = card2;
    }

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }

    @Override
    public String toString() {
        if (card1 == null || card2 == null) return "nullHand";

        return card1.toString() + card2.toString();
    }
}
