package ru.mozevil.model;

public enum TableMap {

    // карманные карты
    HAND_CARD_1(621, 470, 23, 30),//
    HAND_CARD_2(685, 470, 23, 30),//

    // нужно ли делать ход
    CAN_RAISE(1160, 720, 1, 1), //
    CAN_CHECK_CALL(940, 720, 1, 1), //
    CAN_FOLD(750, 720, 1, 1), //

    // общие карты
    TABLE_CARD_1(526,268, 23, 30),//
    TABLE_CARD_2(591,268, 23, 30),//
    TABLE_CARD_3(656,268, 23, 30),//
    TABLE_CARD_4(721,268, 23, 30),//
    TABLE_CARD_5(786,268, 23, 30),//

    // позиция дилера
    DEALER_SEAT_0(770,455, 1, 10),//
    DEALER_SEAT_1(506,441, 1, 10),//
    DEALER_SEAT_2(371,295, 1, 10),//
    DEALER_SEAT_3(409,244, 1, 10),//
    DEALER_SEAT_4(543,184, 1, 10),//
    DEALER_SEAT_5(837,184, 1, 10),//
    DEALER_SEAT_6(958,244, 1, 10),//
    DEALER_SEAT_7(993,290, 1, 10),//
    DEALER_SEAT_8(874,432, 1, 10),//

    // количество активных игроков // SEAT_0 - HERO всегда актив
    ACTIVE_SEAT_1(395, 437, 1, 1),//
    ACTIVE_SEAT_2(277, 289, 1, 1),//
    ACTIVE_SEAT_3(332, 144, 1, 1),//
    ACTIVE_SEAT_4(532, 73, 1, 1),//
    ACTIVE_SEAT_5(835, 73, 1, 1),//
    ACTIVE_SEAT_6(1035, 144, 1, 1),//
    ACTIVE_SEAT_7(1090, 289, 1, 1),//
    ACTIVE_SEAT_8(973, 437, 1, 1),//

    // количесвто игроков за столом // SEAT_0 - HERO всегда за столом
    SEAT_1(423, 492, 1, 4),//
    SEAT_2(305, 344, 1, 4),//
    SEAT_3(360, 199, 1, 4),//
    SEAT_4(560, 128, 1, 4),//
    SEAT_5(804, 128, 1, 4),//
    SEAT_6(1003, 199, 1, 4),//
    SEAT_7(1058, 344, 1, 4),//
    SEAT_8(942, 492, 1, 4),//

    // банк
    POT(610, 225, 150, 16),//

    // стеки игроков
    STACK_SEAT_0(600, 551, 110, 20),//
    STACK_SEAT_1(314, 497, 110, 20),//
    STACK_SEAT_2(195, 349, 110, 20),//
    STACK_SEAT_3(250, 204, 110, 20),//
    STACK_SEAT_4(449, 132, 110, 20),//
    STACK_SEAT_5(805, 132, 110, 20),//
    STACK_SEAT_6(1005, 204, 110, 20),//
    STACK_SEAT_7(1059, 349, 110, 20),//
    STACK_SEAT_8(943, 498, 110, 20),//

    // ставки игроков
    BET_SEAT_0(665, 417, 140, 17),//
    BET_SEAT_1(496, 401, 140, 17),//
    BET_SEAT_2(408, 347, 140, 17),//
    BET_SEAT_3(463, 216, 140, 17),//
    BET_SEAT_4(594, 193, 140, 17),//
    BET_SEAT_5(647, 178, 140, 17),//
    BET_SEAT_6(762, 217, 140, 17),//
    BET_SEAT_7(816, 351, 140, 17),//
    BET_SEAT_8(735, 401, 140, 17),//

    // уровень блаиндов
    LVL(180, 673, 92, 11)//
    ;

    private int x;
    private int y;
    private int w;
    private int h;

    TableMap(int x, int y, int w, int h) {
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