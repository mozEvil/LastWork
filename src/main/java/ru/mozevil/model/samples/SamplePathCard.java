package ru.mozevil.model.samples;

import ru.mozevil.model.Card;

public enum SamplePathCard {
    CARD_1(Card.TWO_OF_CLUBS, "src\\main\\resources\\samples\\cards\\2c.png"),
    CARD_2(Card.TWO_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\2d.png"),
    CARD_3(Card.TWO_OF_HEARTS, "src\\main\\resources\\samples\\cards\\2h.png"),
    CARD_4(Card.TWO_OF_SPADES, "src\\main\\resources\\samples\\cards\\2s.png"),

    CARD_5(Card.THREE_OF_CLUBS, "src\\main\\resources\\samples\\cards\\3c.png"),
    CARD_6(Card.THREE_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\3d.png"),
    CARD_7(Card.THREE_OF_HEARTS, "src\\main\\resources\\samples\\cards\\3h.png"),
    CARD_8(Card.THREE_OF_SPADES, "src\\main\\resources\\samples\\cards\\3s.png"),

    CARD_9(Card.FOUR_OF_CLUBS, "src\\main\\resources\\samples\\cards\\4c.png"),
    CARD_10(Card.FOUR_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\4d.png"),
    CARD_11(Card.FOUR_OF_HEARTS, "src\\main\\resources\\samples\\cards\\4h.png"),
    CARD_12(Card.FOUR_OF_SPADES, "src\\main\\resources\\samples\\cards\\4s.png"),

    CARD_13(Card.FIVE_OF_CLUBS, "src\\main\\resources\\samples\\cards\\5c.png"),
    CARD_14(Card.FIVE_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\5d.png"),
    CARD_15(Card.FIVE_OF_HEARTS, "src\\main\\resources\\samples\\cards\\5h.png"),
    CARD_16(Card.FIVE_OF_SPADES, "src\\main\\resources\\samples\\cards\\5s.png"),

    CARD_17(Card.SIX_OF_CLUBS, "src\\main\\resources\\samples\\cards\\6c.png"),
    CARD_18(Card.SIX_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\6d.png"),
    CARD_19(Card.SIX_OF_HEARTS, "src\\main\\resources\\samples\\cards\\6h.png"),
    CARD_20(Card.SIX_OF_SPADES, "src\\main\\resources\\samples\\cards\\6s.png"),

    CARD_21(Card.SEVEN_OF_CLUBS, "src\\main\\resources\\samples\\cards\\7c.png"),
    CARD_22(Card.SEVEN_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\7d.png"),
    CARD_23(Card.SEVEN_OF_HEARTS, "src\\main\\resources\\samples\\cards\\7h.png"),
    CARD_24(Card.SEVEN_OF_SPADES, "src\\main\\resources\\samples\\cards\\7s.png"),

    CARD_25(Card.EIGHT_OF_CLUBS, "src\\main\\resources\\samples\\cards\\8c.png"),
    CARD_26(Card.EIGHT_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\8d.png"),
    CARD_27(Card.EIGHT_OF_HEARTS, "src\\main\\resources\\samples\\cards\\8h.png"),
    CARD_28(Card.EIGHT_OF_SPADES, "src\\main\\resources\\samples\\cards\\8s.png"),

    CARD_29(Card.NINE_OF_CLUBS, "src\\main\\resources\\samples\\cards\\9c.png"),
    CARD_30(Card.NINE_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\9d.png"),
    CARD_31(Card.NINE_OF_HEARTS, "src\\main\\resources\\samples\\cards\\9h.png"),
    CARD_32(Card.NINE_OF_SPADES, "src\\main\\resources\\samples\\cards\\9s.png"),

    CARD_33(Card.TEN_OF_CLUBS, "src\\main\\resources\\samples\\cards\\Tc.png"),
    CARD_34(Card.TEN_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\Td.png"),
    CARD_35(Card.TEN_OF_HEARTS, "src\\main\\resources\\samples\\cards\\Th.png"),
    CARD_36(Card.TEN_OF_SPADES, "src\\main\\resources\\samples\\cards\\Ts.png"),

    CARD_37(Card.JACK_OF_CLUBS, "src\\main\\resources\\samples\\cards\\Jc.png"),
    CARD_38(Card.JACK_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\Jd.png"),
    CARD_39(Card.JACK_OF_HEARTS, "src\\main\\resources\\samples\\cards\\Jh.png"),
    CARD_40(Card.JACK_OF_SPADES, "src\\main\\resources\\samples\\cards\\Js.png"),

    CARD_41(Card.QUEEN_OF_CLUBS, "src\\main\\resources\\samples\\cards\\Qc.png"),
    CARD_42(Card.QUEEN_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\Qd.png"),
    CARD_43(Card.QUEEN_OF_HEARTS, "src\\main\\resources\\samples\\cards\\Qh.png"),
    CARD_44(Card.QUEEN_OF_SPADES, "src\\main\\resources\\samples\\cards\\Qs.png"),

    CARD_45(Card.KING_OF_CLUBS, "src\\main\\resources\\samples\\cards\\Kc.png"),
    CARD_46(Card.KING_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\Kd.png"),
    CARD_47(Card.KING_OF_HEARTS, "src\\main\\resources\\samples\\cards\\Kh.png"),
    CARD_48(Card.KING_OF_SPADES, "src\\main\\resources\\samples\\cards\\Ks.png"),

    CARD_49(Card.ACE_OF_CLUBS, "src\\main\\resources\\samples\\cards\\Ac.png"),
    CARD_50(Card.ACE_OF_DIAMONDS, "src\\main\\resources\\samples\\cards\\Ad.png"),
    CARD_51(Card.ACE_OF_HEARTS, "src\\main\\resources\\samples\\cards\\Ah.png"),
    CARD_52(Card.ACE_OF_SPADES, "src\\main\\resources\\samples\\cards\\As.png");

    private final Card card;
    private final String filePath;

    SamplePathCard(Card card, String filePath) {
        this.card = card;
        this.filePath = filePath;
    }

    public Card getCard() {
        return card;
    }

    public String getFilePath() {
        return filePath;
    }
}