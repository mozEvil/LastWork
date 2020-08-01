package ru.mozevil.controller.eva;

import ru.mozevil.model.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandTypeEvaluator {

    public static HandType getHandType(Hand hand, Card[] tableCards) {

        HandType result = new HandType();

        result.setType(getType(hand, tableCards));
        result.setStrength(getStrength(hand, tableCards));
        result.setVulnerability(getVulnerability(hand, tableCards));

        result.setOuts(getOuts(hand, tableCards));

        return result;
    }

    /** не готовая, готовая, дро */
    private static int getType(Hand hand, Card[] tableCards) {

        Combination combo = ComboEvaluator.getBestCombo(hand, tableCards);

        // если готовая рука
        if (combo.getTitle() != Combo.HIGH_CARD) return HandType.READY;

        // список карт упорядоченный по рангу от старшего к младшему
        List<Card> cards = getOrderedList(hand, tableCards);

        // если рука не готовая, то ищем дрова
        if (isFlushDraw(cards)) return HandType.DRO;
        if (isOESD(cards)) return HandType.DRO;
        if (isGutshot(cards)) return HandType.DRO;

        // если дров не нашли, то рука не готовая
        return HandType.NOT_READY;
    }

    private static List<Card> getOrderedList(Hand hand, Card[] tableCards) {
        // список карт упорядоченный по рангу от старшего к младшему
        return Stream.concat(Stream.of(hand.getCard1(), hand.getCard2()), Arrays.stream(tableCards))
                .filter(Objects::nonNull)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    private static boolean isFlushDraw(List<Card> cards) {
        // ищем флеш-дро (4 карты одной масти)
        for (Suit suit : Suit.values()) {
            int count = (int) cards.stream().filter(card -> card.getSuit() == suit).count();
            if (count == 4) return true;
        }
        return false;
    }

    private static boolean isOESD(List<Card> cards) {
        // упорядоченный по убыванию список рангов без дубликатов и без Туза
        List<Rank> ranks = cards.stream()
                .map(Card::getRank)
                .filter(rank -> rank != Rank.ACE)
                .distinct()
                .collect(Collectors.toList());

        if (ranks.size() < 4) return false;

        // ищем стрит-дро (4 поочередные карты)
        for (int i = 0; i < (ranks.size() - 3); i++) {
            if (ranks.get(i).previousRank() == ranks.get(i + 1)
                    && ranks.get(i + 1).previousRank() == ranks.get(i + 2)
                    && ranks.get(i + 2).previousRank() == ranks.get(i + 3)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isGutshot(List<Card> cards) {

        // упорядоченный по убыванию список рангов без дубликатов
        List<Rank> ranks = cards.stream()
                .map(Card::getRank)
                .distinct()
                .collect(Collectors.toList());

        if (ranks.size() < 4) return false;

        // ищем стрит с дыркой: AKQ_T, AK_JT, A_QJT
        for (int i = 0; i < (ranks.size() - 3); i++) {
            // AKQ_T
            if (ranks.get(i).previousRank() == ranks.get(i + 1)
                    && ranks.get(i + 1).previousRank() == ranks.get(i + 2)
                    && ranks.get(i + 2).previousRank() == ranks.get(i + 3).nextRank()) {
                return true;
            }
            // AK_JT
            if (ranks.get(i).previousRank() == ranks.get(i + 1)
                    && ranks.get(i + 1).previousRank() == ranks.get(i + 2).nextRank()
                    && ranks.get(i + 2).previousRank() == ranks.get(i + 3)) {
                return true;
            }
            // A_QJT
            if (ranks.get(i).previousRank() == ranks.get(i + 1).nextRank()
                    && ranks.get(i + 1).previousRank() == ranks.get(i + 2)
                    && ranks.get(i + 2).previousRank() == ranks.get(i + 3)) {
                return true;
            }
        }

        // ищем хай гатшот AKQJ_
        if (ranks.get(0) == Rank.ACE) {
            if (ranks.get(0).previousRank() == ranks.get(1)
                    && ranks.get(1).previousRank() == ranks.get(2)
                    && ranks.get(2).previousRank() == ranks.get(3)) {
                return true;
            }
        }

        // ищем лоу гатшот 543_A, 54_2A, 5_32A, _432A
        if (ranks.get(0) == Rank.ACE) {
            // 543_A
            if (ranks.get(1).previousRank() == ranks.get(2)
                    && ranks.get(2).previousRank() == ranks.get(3)
                    && ranks.get(3).previousRank() == ranks.get(0).nextRank()) {
                return true;
            }
            // 54_2A
            if (ranks.get(1).previousRank() == ranks.get(2)
                    && ranks.get(2).previousRank() == ranks.get(3).nextRank()
                    && ranks.get(3).previousRank() == ranks.get(0)) {
                return true;
            }
            // 5_32A
            if (ranks.get(1).previousRank() == ranks.get(2).nextRank()
                    && ranks.get(2).previousRank() == ranks.get(3)
                    && ranks.get(3).previousRank() == ranks.get(0)) {
                return true;
            }
            // _432A
            if (ranks.get(1).previousRank() == ranks.get(2)
                    && ranks.get(2).previousRank() == ranks.get(3)
                    && ranks.get(3).previousRank() == ranks.get(0)) {
                return true;
            }
        }

        return false;
    }

    /** сила руки: сильная, средняя, слабая */
    private static int getStrength(Hand hand, Card[] tableCards) {
        //todo
        //сильные: ТПТК на высоком борде, оверпара, две пары +, дро с 12+ аутов
        //средние: ТП слабый кикер, средняя пара, дро с 8-11 аутов
        //слабые: низкая пара, дро с 4-7 аутов

        return HandType.WEAK;
    }


    private static int getVulnerability(Hand hand, Card[] tableCards) {
        //todo
        //если борд мокрый, а у нас нет дро, то уязвимая
        //если у нас топ пара или оверпара низкого или среднего ранга, то уязвимая
        return HandType.VULNERABLE;
    }

    private static int getOuts(Hand hand, Card[] tableCards) {
        //todo
        //карты которые могут усилить нашу руку до лучшей
        return 0;
    }

}
