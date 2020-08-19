package ru.mozevil.model.map;

public enum TableMapAurora {

    // карманные карты
    HAND_CARD_1(630, 482, 20, 26),
    HAND_CARD_2(689, 482, 20, 26),

    // нужно ли делать ход
    ACTION(1333, 731, 1, 1),

    // общие карты
    TABLE_CARD_1(532,267, 20, 26),
    TABLE_CARD_2(596,267, 20, 26),
    TABLE_CARD_3(660,267, 20, 26),
    TABLE_CARD_4(723,267, 20, 26),
    TABLE_CARD_5(787,267, 20, 26),

    // позиция дилера
    DEALER_SEAT_0(597,460, 1, 10),
    DEALER_SEAT_1(435,375, 1, 10),
    DEALER_SEAT_2(372,274, 1, 10),
    DEALER_SEAT_3(487,210, 1, 10),
    DEALER_SEAT_4(651,164, 1, 10),
    DEALER_SEAT_5(854,182, 1, 10),
    DEALER_SEAT_6(960,236, 1, 10),
    DEALER_SEAT_7(993,373, 1, 10),
    DEALER_SEAT_8(873,425, 1, 10),

    // количество активных игроков // SEAT_0 - HERO всегда актив
    ACTIVE_SEAT_1(384, 427, 1, 1),
    ACTIVE_SEAT_2(282, 280, 1, 1),
    ACTIVE_SEAT_3(325, 139, 1, 1),
    ACTIVE_SEAT_4(515, 69, 1, 1),
    ACTIVE_SEAT_5(856, 69, 1, 1),
    ACTIVE_SEAT_6(1046, 139, 1, 1),
    ACTIVE_SEAT_7(1089, 280, 1, 1),
    ACTIVE_SEAT_8(987, 427, 1, 1),

    // количесвто игроков за столом // SEAT_0 - HERO всегда за столом
    SEAT_1(407, 477, 1, 3),
    SEAT_2(305, 330, 1, 3),
    SEAT_3(347, 189, 1, 3),
    SEAT_4(537, 120, 1, 3),
    SEAT_5(828, 120, 1, 3),
    SEAT_6(1018, 189, 1, 3),
    SEAT_7(1061, 330, 1, 3),
    SEAT_8(958, 477, 1, 3),

    // банк
    POT(631, 237, 104, 12),
    POT2(640, 237, 89, 12),

    // стеки игроков
    STACK_SEAT_0(599, 550, 114, 17),
    STACK_SEAT_1(303, 484, 109, 21),
    STACK_SEAT_2(0, 0, 1, 1),
    STACK_SEAT_3(0, 0, 1, 1),
    STACK_SEAT_4(0, 0, 1, 1),
    STACK_SEAT_5(0, 0, 1, 1),
    STACK_SEAT_6(1015, 196, 112, 18),
    STACK_SEAT_7(0, 0, 1, 1),
    STACK_SEAT_8(0, 0, 1, 1),

    // ставки игроков
    BET_SEAT_0(0, 0, 1, 1),
    BET_SEAT_1(0, 0, 1, 1),
    BET_SEAT_2(0, 0, 1, 1),
    BET_SEAT_3(0, 0, 1, 1),
    BET_SEAT_4(0, 0, 1, 1),
    BET_SEAT_5(0, 0, 1, 1),
    BET_SEAT_6(0, 0, 1, 1),
    BET_SEAT_7(0, 0, 1, 1),
    BET_SEAT_8(0, 0, 1, 1)
    ;

    private int x;
    private int y;
    private int w;
    private int h;

    TableMapAurora(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}