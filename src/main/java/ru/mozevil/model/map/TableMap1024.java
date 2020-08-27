package ru.mozevil.model.map;

public enum TableMap1024 implements TableMap {

    // карманные карты
    HAND_CARD_1(450, 470, 23, 30),//+
    HAND_CARD_2(514, 470, 23, 30),//+

    // нужно ли делать ход
    CAN_RAISE(989, 720, 1, 1), //+
    CAN_CHECK_CALL(769, 720, 1, 1), //+
    CAN_FOLD(579, 720, 1, 1), //+

    // общие карты
    TABLE_CARD_1(355,267, 23, 30),//+
    TABLE_CARD_2(420,267, 23, 30),//+
    TABLE_CARD_3(485,267, 23, 30),//+
    TABLE_CARD_4(550,267, 23, 30),//+
    TABLE_CARD_5(615,267, 23, 30),//+

    // позиция дилера
    DEALER_SEAT_0(599,455, 1, 1),//+
    DEALER_SEAT_1(335,441, 1, 1),//+
    DEALER_SEAT_2(199,295, 1, 1),//+
    DEALER_SEAT_3(237,244, 1, 1),//+
    DEALER_SEAT_4(372,183, 1, 1),//+
    DEALER_SEAT_5(666,183, 1, 1),//+
    DEALER_SEAT_6(788,244, 1, 1),//+
    DEALER_SEAT_7(822,290, 1, 1),//+
    DEALER_SEAT_8(703,432, 1, 1),//+

    // количество активных игроков // SEAT_0 - HERO всегда актив
    ACTIVE_SEAT_1(223, 437, 1, 1),//+
    ACTIVE_SEAT_2(105, 288, 1, 1),//+
    ACTIVE_SEAT_3(160, 143, 1, 1),//+
    ACTIVE_SEAT_4(360, 72, 1, 1),//+
    ACTIVE_SEAT_5(664, 72, 1, 1),//+
    ACTIVE_SEAT_6(864, 143, 1, 1),//+
    ACTIVE_SEAT_7(920, 288, 1, 1),//+
    ACTIVE_SEAT_8(803, 437, 1, 1),//+

    // количесвто игроков за столом // SEAT_0 - HERO всегда за столом
    SEAT_1(249, 493, 1, 3),//+-
    SEAT_2(131, 345, 1, 3),//+-
    SEAT_3(186, 200, 1, 3),//+-
    SEAT_4(386, 128, 1, 3),//+-
    SEAT_5(636, 128, 1, 3),//+-
    SEAT_6(836, 200, 1, 3),//+-
    SEAT_7(891, 345, 1, 3),//+-
    SEAT_8(774, 493, 1, 3),//+-

    // банк
    POT(439, 225, 150, 16),//+

    // стеки игроков
    STACK_SEAT_0(429, 551, 110, 20),//+
    STACK_SEAT_1(143, 497, 110, 20),//+
    STACK_SEAT_2(24, 349, 110, 20),//+
    STACK_SEAT_3(79, 204, 110, 20),//+
    STACK_SEAT_4(278, 132, 110, 20),//+
    STACK_SEAT_5(634, 132, 110, 20),//+
    STACK_SEAT_6(834, 204, 110, 20),//+
    STACK_SEAT_7(888, 349, 110, 20),//+
    STACK_SEAT_8(772, 498, 110, 20),//+

    // ставки игроков
    BET_SEAT_0(494, 416, 140, 17),//+
    BET_SEAT_1(325, 401, 140, 17),//+
    BET_SEAT_2(237, 347, 140, 17),//+
    BET_SEAT_3(292, 215, 140, 17),//+
    BET_SEAT_4(423, 192, 140, 17),//+
    BET_SEAT_5(476, 178, 140, 17),//+
    BET_SEAT_6(592, 216, 140, 17),//+
    BET_SEAT_7(645, 351, 140, 17),//+
    BET_SEAT_8(564, 401, 140, 17),//+

    // уровень блаиндов
    LVL(9, 673, 92, 11)//+
    ;

    private final int x;
    private final int y;
    private final int w;
    private final int h;

    TableMap1024(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getW() {
        return w;
    }

    @Override
    public int getH() {
        return h;
    }
}