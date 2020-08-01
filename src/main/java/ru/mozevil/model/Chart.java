package ru.mozevil.model;

import java.util.HashMap;

public class Chart {

    private final HashMap<Hand, Double> map = new HashMap<>();

    /** handName must be: 77, AK, AJo, T9s
     * stackSize - максимальный размер стека для пуша с данной рукой */
    public void addHand(String handName, double stackSize) {

        Rank r1 = Rank.getRank(handName.substring(0, 1));
        Rank r2 = Rank.getRank(handName.substring(1, 2));

        if (handName.length() == 2) {
            // 77
            if (r1 == r2) {
                for (Suit s1 : Suit.values()) {
                    Card c1 = Card.getCard(r1, s1);
                    for (Suit s2 : Suit.values()) {
                        if (s1 != s2) {
                            Card c2 = Card.getCard(r2, s2);
                            Hand hand = HANDS.GET.hand(c1, c2);
                            map.put(hand, stackSize);
                        }
                    }
                }
            } else { // AK
                for (Suit s1 : Suit.values()) {
                    Card c1 = Card.getCard(r1, s1);
                    for (Suit s2 : Suit.values()) {
                        Card c2 = Card.getCard(r2, s2);
                        if (c1 != c2) {
                            Hand hand = HANDS.GET.hand(c1, c2);
                            map.put(hand, stackSize);

                            hand = HANDS.GET.hand(c2, c1);
                            map.put(hand, stackSize);
                        }
                    }
                }
            }
        }

        if (handName.length() == 3) {
            String type = handName.substring(2, 3);
            // AJo
            if (type.equals("o")) {
                for (Suit s1 : Suit.values()) {
                    Card c1 = Card.getCard(r1, s1);
                    for (Suit s2 : Suit.values()) {
                        if (s1 != s2) {
                            Card c2 = Card.getCard(r2, s2);

                            Hand hand = HANDS.GET.hand(c1, c2);
                            map.put(hand, stackSize);

                            hand = HANDS.GET.hand(c2, c1);
                            map.put(hand, stackSize);
                        }
                    }
                }
            }
            // T9s
            if (type.equals("s")) {
                for (Suit s1 : Suit.values()) {
                    Card c1 = Card.getCard(r1, s1);
                    Card c2 = Card.getCard(r2, s1);

                    Hand hand = HANDS.GET.hand(c1, c2);
                    map.put(hand, stackSize);

                    hand = HANDS.GET.hand(c2, c1);
                    map.put(hand, stackSize);
                }
            }
        }

    }

    public void addRange(Range range, double stackSize) {
        for (Hand h : range.getHands()) {
            map.put(h, stackSize);
        }
    }

    public boolean isCanPush(Hand hand, double effStack) {
        return map.get(hand) >= effStack;
    }
}
