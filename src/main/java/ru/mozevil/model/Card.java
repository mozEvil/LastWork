package ru.mozevil.model;

public enum Card {
    TWO_OF_CLUBS(1, Rank.TWO, Suit.CLUBS),
    TWO_OF_DIAMONDS(2, Rank.TWO, Suit.DIAMONDS),
    TWO_OF_HEARTS(3, Rank.TWO, Suit.HEARTS),
    TWO_OF_SPADES(4, Rank.TWO, Suit.SPADES),

    THREE_OF_CLUBS(5, Rank.THREE, Suit.CLUBS),
    THREE_OF_DIAMONDS(6, Rank.THREE, Suit.DIAMONDS),
    THREE_OF_HEARTS(7, Rank.THREE, Suit.HEARTS),
    THREE_OF_SPADES(8, Rank.THREE, Suit.SPADES),

    FOUR_OF_CLUBS(9, Rank.FOUR, Suit.CLUBS),
    FOUR_OF_DIAMONDS(10, Rank.FOUR, Suit.DIAMONDS),
    FOUR_OF_HEARTS(11, Rank.FOUR, Suit.HEARTS),
    FOUR_OF_SPADES(12, Rank.FOUR, Suit.SPADES),

    FIVE_OF_CLUBS(13, Rank.FIVE, Suit.CLUBS),
    FIVE_OF_DIAMONDS(14, Rank.FIVE, Suit.DIAMONDS),
    FIVE_OF_HEARTS(15, Rank.FIVE, Suit.HEARTS),
    FIVE_OF_SPADES(16, Rank.FIVE, Suit.SPADES),

    SIX_OF_CLUBS(17, Rank.SIX, Suit.CLUBS),
    SIX_OF_DIAMONDS(18, Rank.SIX, Suit.DIAMONDS),
    SIX_OF_HEARTS(19, Rank.SIX, Suit.HEARTS),
    SIX_OF_SPADES(20, Rank.SIX, Suit.SPADES),

    SEVEN_OF_CLUBS(21, Rank.SEVEN, Suit.CLUBS),
    SEVEN_OF_DIAMONDS(22, Rank.SEVEN, Suit.DIAMONDS),
    SEVEN_OF_HEARTS(23, Rank.SEVEN, Suit.HEARTS),
    SEVEN_OF_SPADES(24, Rank.SEVEN, Suit.SPADES),

    EIGHT_OF_CLUBS(25, Rank.EIGHT, Suit.CLUBS),
    EIGHT_OF_DIAMONDS(26, Rank.EIGHT, Suit.DIAMONDS),
    EIGHT_OF_HEARTS(27, Rank.EIGHT, Suit.HEARTS),
    EIGHT_OF_SPADES(28, Rank.EIGHT, Suit.SPADES),

    NINE_OF_CLUBS(29, Rank.NINE, Suit.CLUBS),
    NINE_OF_DIAMONDS(30, Rank.NINE, Suit.DIAMONDS),
    NINE_OF_HEARTS(31, Rank.NINE, Suit.HEARTS),
    NINE_OF_SPADES(32, Rank.NINE, Suit.SPADES),

    TEN_OF_CLUBS(33, Rank.TEN, Suit.CLUBS),
    TEN_OF_DIAMONDS(34, Rank.TEN, Suit.DIAMONDS),
    TEN_OF_HEARTS(35, Rank.TEN, Suit.HEARTS),
    TEN_OF_SPADES(36, Rank.TEN, Suit.SPADES),

    JACK_OF_CLUBS(37, Rank.JACK, Suit.CLUBS),
    JACK_OF_DIAMONDS(38, Rank.JACK, Suit.DIAMONDS),
    JACK_OF_HEARTS(39, Rank.JACK, Suit.HEARTS),
    JACK_OF_SPADES(40, Rank.JACK, Suit.SPADES),

    QUEEN_OF_CLUBS(41, Rank.QUEEN, Suit.CLUBS),
    QUEEN_OF_DIAMONDS(42, Rank.QUEEN, Suit.DIAMONDS),
    QUEEN_OF_HEARTS(43, Rank.QUEEN, Suit.HEARTS),
    QUEEN_OF_SPADES(44, Rank.QUEEN, Suit.SPADES),

    KING_OF_CLUBS(45, Rank.KING, Suit.CLUBS),
    KING_OF_DIAMONDS(46, Rank.KING, Suit.DIAMONDS),
    KING_OF_HEARTS(47, Rank.KING, Suit.HEARTS),
    KING_OF_SPADES(48, Rank.KING, Suit.SPADES),

    ACE_OF_CLUBS(49, Rank.ACE, Suit.CLUBS),
    ACE_OF_DIAMONDS(50, Rank.ACE, Suit.DIAMONDS),
    ACE_OF_HEARTS(51, Rank.ACE, Suit.HEARTS),
    ACE_OF_SPADES(52, Rank.ACE, Suit.SPADES);

    private final int id;
    private transient final Rank rank;
    private transient final Suit suit;

    Card(int id, Rank rank, Suit suit) {
        this.id = id;
        this.rank = rank;
        this.suit = suit;
    }

    public int getId() {
        return id;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public String getName() {
        return rank.getName() + suit.getName();
    }

    @Override
    public String toString() {
        return rank.getName() + suit.getIcon();
    }

    public static Card getCard(Rank rank, Suit suit) {
        for (Card card : Card.values()) {
            if (card.getRank() == rank && card.getSuit() == suit)
                return card;
        }
        return null;
    }
}
