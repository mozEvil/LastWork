package ru.mozevil.controller.eva;

import org.jetbrains.annotations.NotNull;
import ru.mozevil.model.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComboEvaluator {

    private static boolean pair;
    private static boolean twoPairs;
    private static boolean set;
    private static boolean straight;
    private static boolean flush;
    private static boolean fullHouse;
    private static boolean quads;
    private static boolean straightFlush;
    private static boolean royalFlush;

    private static Suit flushSuit;
    private static int straightStartIndex;
    private static int straightFlushStartIndex;


    /** Возвращает самую сильную комбинацию, которую можно собрать из данных 5-7-ми карт*/
    public static Combination getBestCombo(Hand hand, Card[] tableCards) {
        if (hand == null || tableCards == null) return null;

        pair = false;
        twoPairs = false;
        set = false;
        straight = false;
        flush = false;
        fullHouse = false;
        quads = false;
        straightFlush = false;
        royalFlush = false;

        List<Card> cards = getOrderedList(hand, tableCards);

        return getBestCombo(cards);
    }

    private static List<Card> getOrderedList(@NotNull Hand hand, @NotNull Card[] tableCards) {

        return Stream.concat(Stream.of(hand.getCard1(), hand.getCard2()), Arrays.stream(tableCards))
                .filter(Objects::nonNull)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    /** Возвращает самую сильную 5-ти карточную комбинацию из списка 5-7-ми карт */
    private static Combination getBestCombo(@NotNull List<Card> cards) {

        findStraight(cards);
        findFlush(cards);

        if (straight && flush) {

            findStraightFlush(cards);
            if (straightFlush) {

                findRoyalFlush(cards);
                if (royalFlush) {
                    return getRoyalFlush(cards); // royal flush
                }

                return getStraightFlush(cards); // straight flush
            }
        }

        if (!straight && !flush) {

            findByRank(cards);

            if (quads) {
                return getQuads(cards); // quads
            }

            if (fullHouse) {
                return getFullHouse(cards); // full house
            }
        }

        if (flush) {
            return getFlush(cards); // flush
        }

        if (straight) {
            return getStraight(cards); // straight
        }

        if (set) {
            return getSet(cards); // set
        }

        if (twoPairs) {
            return getTwoPairs(cards); // two pairs
        }

        if (pair) {
            return getPair(cards); // pair
        }

        return getHighCard(cards); // High card
    }

    private static void findByRank(List<Card> cards) {
        EnumMap<Rank, Integer> map = new EnumMap<>(Rank.class);

        // считаем карты с одинаковым рангом
        for (Card c : cards) {
            Integer oldValue = map.put(c.getRank(), 1);

            if (oldValue != null) {
                map.put(c.getRank(), oldValue + 1);
            }
        }

        int twoPairsCount = 0;
        for (int val : map.values()) {
            if (val == 2) {
                twoPairsCount++;
                pair = true;
            }
            if (val == 3) {
                set = true;
            }
            if (val == 4) {
                quads = true;
            }
        }

        if (twoPairsCount >= 2) {
            twoPairs = true;
        }

        if (pair && set) {
            fullHouse = true;
        }
    }

    private static void findStraight(List<Card> cards) {
        LinkedHashSet<Rank> ranks = new LinkedHashSet<>();
        // избавляемся от дубликатов по рангу
        for (Card card : cards) {
            ranks.add(card.getRank());
        }

        if (ranks.size() < 5) {
            return;
        }

        List<Rank> list = new ArrayList<>(ranks);
//        listR.sort(Collections.reverseOrder());

        for (int i = 0; i < (list.size() - 4); i++) {
            if (list.get(i).previousRank() == list.get(i + 1)
                    && list.get(i + 1).previousRank() == list.get(i + 2)
                    && list.get(i + 2).previousRank() == list.get(i + 3)
                    && list.get(i + 3).previousRank() == list.get(i + 4)) {

                straight = true;
                straightStartIndex = i;
                return;
            }
        }

        // ищем Лоу стирт
        int i = list.size() - 4;
        if (list.get(i).previousRank() == list.get(i+1)
                && list.get(i+1).previousRank() == list.get(i+2)
                && list.get(i+2).previousRank() == list.get(i+3)
                && list.get(i+3).previousRank() == list.get(0)) {

            straight = true;
            straightStartIndex = i;
        }

    }

    private static void findFlush(List<Card> cards) {
        EnumMap<Suit, Integer> map = new EnumMap<>(Suit.class);

        // считаем количество карт одной масти, для каждой масти
        for (Card c : cards) {
            Integer oldValue = map.put(c.getSuit(), 1);

            if (oldValue != null) {
                map.put(c.getSuit(), oldValue + 1);
            }
        }

        // определяем наличие флеша и его масть
        for (Map.Entry<Suit, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 5) {
                flush = true;
                flushSuit = entry.getKey();
                return;
            }
        }
    }

    private static void findStraightFlush(List<Card> cards) {
        List<Card> list = new ArrayList<>(cards);
        // удаляем не нашу масть
        list.removeIf(card -> card.getSuit() != flushSuit);

        if (list.size() < 5) {
            return;
        }

        for (int i = 0; i < (list.size() - 4); i++) {
            if (list.get(i).getRank().previousRank() == list.get(i+1).getRank()
                    && list.get(i+1).getRank().previousRank() == list.get(i+2).getRank()
                    && list.get(i+2).getRank().previousRank() == list.get(i+3).getRank()
                    && list.get(i+3).getRank().previousRank() == list.get(i+4).getRank()) {

                straightFlush = true;
                straightFlushStartIndex = i;
                return;
            }
        }
        // ищем Лоу стирт-флеш
        int i = list.size() - 4; // min=1, max=3
        if (list.get(i).getRank().previousRank() == list.get(i+1).getRank()
                && list.get(i+1).getRank().previousRank() == list.get(i+2).getRank()
                && list.get(i+2).getRank().previousRank() == list.get(i+3).getRank()
                && list.get(i+3).getRank().previousRank() == list.get(0).getRank()) {

            straightFlush = true;
            straightFlushStartIndex = i;
        }
    }

    private static void findRoyalFlush(List<Card> cards) {
        if (cards.get(straightFlushStartIndex).getRank() == Rank.ACE) {
            royalFlush = true;
        }
    }

    private static Combination getHighCard(List<Card> cards) {
        Combination combo = new Combination();
        for (int i = 0; i < 5; i++) {
            combo.addCard(cards.get(i));
        }
        combo.setTitle(Combo.HIGH_CARD);

        return combo;
    }

    private static Combination getPair(List<Card> cards) {
        Combination combo = new Combination();

        for (int i = 0; i <= 5; i++) {
            if (cards.get(i).getRank() == cards.get(i+1).getRank()) {
                combo.addCard(cards.remove(i));
                combo.addCard(cards.remove(i));
                break;
            }
        }

        combo.addCard(cards.get(0));
        combo.addCard(cards.get(1));
        combo.addCard(cards.get(2));

        combo.setTitle(Combo.PAIR);

        return combo;
    }

    private static Combination getTwoPairs(List<Card> cards) {
        Combination combo = new Combination();
        int count = 0;

        for (int i = 0; i <= 5; i++) {
            if (cards.get(i).getRank() == cards.get(i+1).getRank()) {
                combo.addCard(cards.remove(i));
                combo.addCard(cards.remove(i));
                i--;
                count++;
                if (count == 2) {
                    break;
                }
            }
        }

        combo.addCard(cards.get(0));

        combo.setTitle(Combo.TWO_PAIRS);

        return combo;
    }

    private static Combination getSet(List<Card> cards) {
        Combination combo = new Combination();

        for (int i = 0; i <= 4; i++) {
            if (cards.get(i).getRank() == cards.get(i+1).getRank() &&
                    cards.get(i+1).getRank() == cards.get(i+2).getRank()) {
                combo.addCard(cards.remove(i));
                combo.addCard(cards.remove(i));
                combo.addCard(cards.remove(i));
                break;
            }
        }

        combo.addCard(cards.get(0));
        combo.addCard(cards.get(1));

        combo.setTitle(Combo.THREE_OF_A_KIND);

        return combo;
    }

    private static Combination getStraight(List<Card> cards) {
        Combination combo = new Combination();

        //удалить дубликаты рангов
        for (int i = 0; i < (cards.size() - 1); i++) {
            if (cards.get(i).getRank() == cards.get(i+1).getRank()) {
                cards.remove(i);
                i--;
            }
        }

        if (straightStartIndex < (cards.size() - 4)) {
            for (int i = 0; i < 5; i++) {
                combo.addCard(cards.get(straightStartIndex + i));
            }
        } else {
            for (int i = 0; i < 4; i++) {
                combo.addCard(cards.get(straightStartIndex + i));
            }
            combo.addCard(cards.get(0));
        }

        combo.setTitle(Combo.STRAIGHT);

        return combo;
    }

    private static Combination getFlush(List<Card> cards) {
        Combination combo = new Combination();

        for (Card card : cards) {
            if (card.getSuit() == flushSuit) {
                combo.addCard(card);
            }
            if (combo.getCount() == 5) {
                break;
            }
        }

        combo.setTitle(Combo.FLUSH);

        return combo;
    }

    private static Combination getFullHouse(List<Card> cards) {
        Combination combo = new Combination();

        for (int i = 0; i <= 4; i++) {
            if (cards.get(i).getRank() == cards.get(i+1).getRank()
                    && cards.get(i).getRank() == cards.get(i+2).getRank()) {
                combo.addCard(cards.remove(i));
                combo.addCard(cards.remove(i));
                combo.addCard(cards.remove(i));
                break;
            }
        }

        for (int i = 0; i <= 2; i++) {
            if (cards.get(i).getRank() == cards.get(i+1).getRank()) {
                combo.addCard(cards.get(i));
                combo.addCard(cards.get(i+1));
                break;
            }
        }

        combo.setTitle(Combo.FULL_HOUSE);

        return combo;
    }

    private static Combination getQuads(List<Card> cards) {
        Combination combo = new Combination();

        for (int i = 0; i <= 3; i++) {
            if (cards.get(i).getRank() == cards.get(i+1).getRank() 
                    && cards.get(i+1).getRank() == cards.get(i+2).getRank() 
                    && cards.get(i+2).getRank() == cards.get(i+3).getRank()) {
                combo.addCard(cards.remove(i));
                combo.addCard(cards.remove(i));
                combo.addCard(cards.remove(i));
                combo.addCard(cards.remove(i));
                break;
            }
        }

        combo.addCard(cards.get(0));

        combo.setTitle(Combo.FOUR_OF_A_KIND);

        return combo;
    }

    private static Combination getStraightFlush(List<Card> cards) {
        Combination combo = new Combination();

        // удаляем не нашу масть
        cards.removeIf(card -> card.getSuit() != flushSuit);

        if (straightFlushStartIndex < (cards.size() - 4)) {
            for (int i = 0; i < 5; i++) {
                combo.addCard(cards.get(straightFlushStartIndex + i));
            }
        } else {
            for (int i = 0; i < 4; i++) {
                combo.addCard(cards.get(straightFlushStartIndex + i));
            }
            combo.addCard(cards.get(0));
        }

        combo.setTitle(Combo.STRAIGHT_FLUSH);

        return combo;
    }

    private static Combination getRoyalFlush(List<Card> cards) {
        Combination combo = getStraightFlush(cards);
        combo.setTitle(Combo.ROYAL_FLUSH);

        return combo;
    }

}
