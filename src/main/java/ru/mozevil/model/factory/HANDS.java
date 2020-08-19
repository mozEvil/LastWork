package ru.mozevil.model.factory;

import ru.mozevil.model.Card;
import ru.mozevil.model.Hand;
import ru.mozevil.model.Rank;
import ru.mozevil.model.Suit;

import java.util.LinkedList;

public class HANDS {

    public static final HANDS GET = new HANDS();

    private final LinkedList<Hand> hands = new LinkedList<>();

    private HANDS() {
        for (Card c1 : Card.values()) {
            for (Card c2 : Card.values()) {
                if (c1 != c2) {
                    hands.add(new Hand(c1, c2));
                }
            }
        }
    }

    public Hand hand(String name) {

        if (name.length() != 4) return null;

        String r1 = name.substring(0,1);
        String s1 = name.substring(1,2);
        String r2 = name.substring(2,3);
        String s2 = name.substring(3,4);

        Rank rank1 = Rank.getRank(r1);
        Rank rank2 = Rank.getRank(r2);
        Suit suit1 = Suit.getSuit(s1);
        Suit suit2 = Suit.getSuit(s2);

        Card c1 = Card.getCard(rank1, suit1);
        Card c2 = Card.getCard(rank2, suit2);

        return hand(c1, c2);
    }

    public Hand hand(Card c1, Card c2) {
        for (Hand h : hands) {
            if (h.getCard1() == c1 && h.getCard2() == c2) return h;
        }
        return null;
    }
}
