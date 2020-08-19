package ru.mozevil.model;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import ru.mozevil.model.factory.HANDS;

import java.util.LinkedHashSet;
import java.util.Set;

public class Range {

    private static final Logger log = Logger.getLogger(Range.class.getName());

    private final Set<Hand> range = new LinkedHashSet<>();

    private final int CODE_ERROR = 0;
    private final int CODE_RANGE = 1;
    private final int CODE_PAIRS = 2;
    private final int CODE_CONNECTORS = 3;

    public Range() {
    }

    public Range(String range) {
        addAll(range);
    }

    public Set<Hand> getHands() {
        return range;
    }

    /** "22+,AT-AK,54s, KsQd" etc... */
    public void addAll(String name) {
        for (String range : name.split(",")) {
            add(range.trim());
        }
    }

    /** 22+, A2+, A2s+, A2o+, 54+, 54s+, 54o+, J9+, J9s+, J9o+, JT++, JTs++, JTo++, AK, AKs, AKo, AcKd,
     * A2-A9, A2s-A9s, A2o-A9o, 54-JT, 54s-JTs, 54o-JTo, 22-77, All  */
    public void add(String name) {

        if (name.length() < 2) return;

        if (name.matches("[Aa]ll")) {
            addAll("22+, 32++");
            return;
        }

        if (name.matches("[AKQJT98765432][AKQJT98765432][so]?-[AKQJT98765432][AKQJT98765432][so]?")) {
            addHandsFromTo(name);
            return;
        }

        if (name.matches("[AKQJT98765432][cdhs][AKQJT98765432][cdhs]")) {
            addHand(name);
            return;
        }

        String c1 = name.substring(0, 1);
        String c2 = name.substring(1, 2);

        Rank r1 = Rank.getRank(c1);
        Rank r2 = Rank.getRank(c2);

        if (name.matches("[AKQJT98765432][AKQJT98765432]")) {
            addHand(r1, r2);
            return;
        }

        if (name.matches("[AKQJT98765432][AKQJT98765432]o")) {
            addHand_o(r1, r2);
            return;
        }

        if (name.matches("[AKQJT98765432][AKQJT98765432]s")) {
            addHand_s(r1, r2);
            return;
        }

        if (name.matches("[AKQJT98765432][AKQJT98765432]\\+")) {
            //addRange, addPairs, addConnectors
            int code = toDetermine(r1, r2);

            switch (code) {
                case CODE_RANGE :
                    addRange(r1, r2);
                    break;

                case CODE_PAIRS :
                    addPairs(r1);
                    break;

                case CODE_CONNECTORS :
                    addConnectors(r1, r2);
                    break;
            }

            return;
        }

        if (name.matches("[AKQJT98765432][AKQJT98765432]o\\+")) {
            //addRange_o, addConnectors_o
            int code = toDetermine(r1, r2);

            switch (code) {
                case CODE_RANGE :
                    addRange_o(r1, r2);
                    break;

                case CODE_CONNECTORS :
                    addConnectors_o(r1, r2);
                    break;
            }

            return;
        }

        if (name.matches("[AKQJT98765432][AKQJT98765432]s\\+")) {
            //addRange_s, addConnectors_s
            int code = toDetermine(r1, r2);

            switch (code) {
                case CODE_RANGE :
                    addRange_s(r1, r2);
                    break;

                case CODE_CONNECTORS :
                    addConnectors_s(r1, r2);
                    break;
            }
            return;
        }

        if (name.matches("[AKQJT98765432][AKQJT98765432]\\+\\+")) {
            addRange2(r1, r2);
            return;
        }

        if (name.matches("[AKQJT98765432][AKQJT98765432]o\\+\\+")) {
            addRange2_o(r1, r2);
            return;
        }

        if (name.matches("[AKQJT98765432][AKQJT98765432]s\\+\\+")) {
            addRange2_s(r1, r2);
            return;
        }

        // что-то пошло не так
        log.error("ERROR ADD RANGE");
    }

    // 22-77, A2s-A9s, ATo-AKo, JT-KQ
    private void addHandsFromTo(String name) {
        String start = name.split("-")[0];
        String end = name.split("-")[1];

        String c1 = start.substring(0, 1);
        String c2 = start.substring(1, 2);
        String c3 = end.substring(0, 1);
        String c4 = end.substring(1, 2);
        String c5 = "";
        String c6 = "";
        String so = "";

        if (start.length() == 3 && end.length() == 3) {
            c5 = start.substring(2, 3);
            c6 = end.substring(2, 3);
            if (c5.equals(c6)) so = c5;
        }

        boolean all = so.length() == 0;
        boolean suited = so.equals("s");
        boolean offsuited = so.equals("o");

        Rank r1s = Rank.getRank(c1);
        Rank r2s = Rank.getRank(c2);
        Rank r1e = Rank.getRank(c3);
        Rank r2e = Rank.getRank(c4);

        //77-99
        if (r1s == r2s && r1e == r2e) {
            while (r1s != r1e.nextRank()) {
                addPairs(r1s);
                r1s = r1s.nextRank();
            }
            return;
        }

        // A2-AK
        if (r1s == r1e) {
            // A2-AK
            if (all) {
                while (r2s != r2e.nextRank()) {
                    addHand(r1s, r2s);
                    r2s = r2s.nextRank();
                }
                return;
            }
            // A2s-AKs
            if (suited) {
                while (r2s != r2e.nextRank()) {
                    addHand_s(r1s, r2s);
                    r2s = r2s.nextRank();
                }
                return;
            }
            // A2o-AKo
            if (offsuited) {
                while (r2s != r2e.nextRank()) {
                    addHand_o(r1s, r2s);
                    r2s = r2s.nextRank();
                }
                return;
            }
        }

        // JT-KQ
        if (r1s.previousRank() == r2s && r1e.previousRank() == r2e) {
            // JT-KQ
            if (all) {
                while (r1s != r1e.nextRank() && r2s != r2e.nextRank()) {
                    addHand(r1s, r2s);
                    r1s = r1s.nextRank();
                    r2s = r2s.nextRank();
                }
                return;
            }
            // JTs-KQs
            if (suited) {
                while (r1s != r1e.nextRank() && r2s != r2e.nextRank()) {
                    addHand_s(r1s, r2s);
                    r1s = r1s.nextRank();
                    r2s = r2s.nextRank();
                }
                return;
            }
            // JTo-KQo
            if (offsuited) {
                while (r1s != r1e.nextRank() && r2s != r2e.nextRank()) {
                    addHand_o(r1s, r2s);
                    r1s = r1s.nextRank();
                    r2s = r2s.nextRank();
                }
                return;
            }
        }

    }private int toDetermine(Rank r1, Rank r2) {

        if (r1 == r2) return CODE_PAIRS;
        if (r1.ordinal() < r2.ordinal()) return CODE_ERROR;
        if (r1 != Rank.ACE && r1.previousRank() == r2) return CODE_CONNECTORS;

        return CODE_RANGE;
    }

    public void addHand(Card c1, Card c2) {
        if (c1 == c2) return;

        range.add(HANDS.GET.hand(c1, c2));

        if (c1.getRank() != c2.getRank()) {
            range.add(HANDS.GET.hand(c2, c1));
        }
    }

    /** AT */
    private void addHand(Rank r1, Rank r2) {
        for (Suit s1 : Suit.values()) {
            Card c1 = Card.getCard(r1, s1);
            for (Suit s2 : Suit.values()) {
                Card c2 = Card.getCard(r2, s2);
                if (c1 != c2) {
                    addHand(c1, c2);
                }
            }
        }
    }

    /** ATo */
    private void addHand_o(Rank r1, Rank r2) {
        for (Suit s1 : Suit.values()) {
            Card c1 = Card.getCard(r1, s1);
            for (Suit s2 : Suit.values()) {
                if (s1 != s2) {
                    Card c2 = Card.getCard(r2, s2);
                    addHand(c1, c2);
                }
            }
        }
    }

    /** ATs */
    private void addHand_s(Rank r1, Rank r2) {
        for (Suit s1 : Suit.values()) {
            Card c1 = Card.getCard(r1, s1);
            Card c2 = Card.getCard(r2, s1);
            addHand(c1, c2);
        }
    }

    /** AcKd */
    public void addHand(@NotNull String name) {
        if (name.length() != 4) return;

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

        addHand(c1, c2);
    }

    /** A2+ */
    private void addRange(Rank r1, Rank r2) {
        while (r1 != r2) {
            for (Suit s1 : Suit.values()) {
                Card c1 = Card.getCard(r1, s1);
                for (Suit s2 : Suit.values()) {
                    Card c2 = Card.getCard(r2, s2);
                    addHand(c1, c2);
                }
            }
            r2 = r2.nextRank();
        }
    }

    /** A2s+ */
    private void addRange_s(Rank r1, Rank r2) {
        while (r1 != r2) {
            for (Suit s : Suit.values()) {
                Card c1 = Card.getCard(r1, s);
                Card c2 = Card.getCard(r2, s);
                addHand(c1, c2);
            }
            r2 = r2.nextRank();
        }
    }

    /** A2o+ */
    private void addRange_o(Rank r1, Rank r2) {
        while (r1 != r2) {
            for (Suit s1 : Suit.values()) {
                Card c1 = Card.getCard(r1, s1);
                for (Suit s2 : Suit.values()) {
                    if (s1 != s2) {
                        Card c2 = Card.getCard(r2, s2);
                        addHand(c1, c2);
                    }
                }
            }
            r2 = r2.nextRank();
        }
    }

    /** 22+ */
    private void addPairs(Rank rank) {
        while (rank != Rank.ACE) {
            for (Suit s1 : Suit.values()) {
                Card c1 = Card.getCard(rank, s1);
                for (Suit s2 : Suit.values()) {
                    if (s1 != s2) {
                        Card c2 = Card.getCard(rank, s2);
                        addHand(c1, c2);
                    }
                }
            }
            rank = rank.nextRank();
        }
        //add AA
        for (Suit s1 : Suit.values()) {
            Card c1 = Card.getCard(rank, s1);
            for (Suit s2 : Suit.values()) {
                if (s1 != s2) {
                    Card c2 = Card.getCard(rank, s2);
                    addHand(c1, c2);
                }
            }
        }
    }

    /** T9+ */
    private void addConnectors(Rank r1, Rank r2) {
        while (r1 != Rank.TWO) {
            for (Suit s1 : Suit.values()) {
                Card c1 = Card.getCard(r1, s1);
                for (Suit s2 : Suit.values()) {
                    Card c2 = Card.getCard(r2, s2);
                    addHand(c1, c2);
                }
            }
            r1 = r1.nextRank();
            r2 = r2.nextRank();
        }
    }

    /** T9s+ */
    private void addConnectors_s(Rank r1, Rank r2) {
        while (r1 != Rank.TWO) {
            for (Suit s1 : Suit.values()) {
                Card c1 = Card.getCard(r1, s1);
                Card c2 = Card.getCard(r2, s1);
                addHand(c1, c2);
            }
            r1 = r1.nextRank();
            r2 = r2.nextRank();
        }
    }

    /** T9o+ */
    private void addConnectors_o(Rank r1, Rank r2) {
        while (r1 != Rank.TWO) {
            for (Suit s1 : Suit.values()) {
                Card c1 = Card.getCard(r1, s1);
                for (Suit s2 : Suit.values()) {
                    if (s1 != s2) {
                        Card c2 = Card.getCard(r2, s2);
                        addHand(c1, c2);
                    }
                }
            }
            r1 = r1.nextRank();
            r2 = r2.nextRank();
        }
    }

    /** JT++ */
    private void addRange2(Rank r1, Rank r2) {
        while (r1 != Rank.TWO) {
               addRange(r1, r2);
               r1 = r1.nextRank();
        }
    }

    /** JTs++ */
    private void addRange2_s(Rank r1, Rank r2) {
        while (r1 != Rank.TWO) {
            addRange_s(r1, r2);
            r1 = r1.nextRank();
        }
    }

    /** JTo++ */
    private void addRange2_o(Rank r1, Rank r2) {
        while (r1 != Rank.TWO) {
            addRange_o(r1, r2);
            r1 = r1.nextRank();
        }
    }

    public boolean containsHand(Hand hand) {
        return range.contains(hand);
    }


}
