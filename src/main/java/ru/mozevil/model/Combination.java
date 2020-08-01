package ru.mozevil.model;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    private final List<Card> cards = new ArrayList<>(5);
    private Combo title;

    public Combo getTitle() {
        return title;
    }

    public void setTitle(Combo title) {
        this.title = title;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public int getCount() {
        return cards.size();
    }

    @Override
    public String toString() {
        return getTitle() + " " + cards.toString();
    }

}
