package ru.mozevil.controller.parser.cutter;

import ru.mozevil.model.map.TableMap1366;
import java.awt.image.BufferedImage;

public class TableCutter1366 extends Cutter {

    @Override
    public BufferedImage getHandCardImg(int i) {
        switch (i) {
            case (1) : return cutTable(TableMap1366.HAND_CARD_1);
            case (2) : return cutTable(TableMap1366.HAND_CARD_2);
            default: return null;
        }
    }

    @Override
    public BufferedImage getTableCardImg(int i) {
        switch (i) {
            case (1) : return cutTable(TableMap1366.TABLE_CARD_1);
            case (2) : return cutTable(TableMap1366.TABLE_CARD_2);
            case (3) : return cutTable(TableMap1366.TABLE_CARD_3);
            case (4) : return cutTable(TableMap1366.TABLE_CARD_4);
            case (5) : return cutTable(TableMap1366.TABLE_CARD_5);
            default: return null;
        }
    }

    @Override
    public BufferedImage getSeatImg(int i) {
        switch (i) {
            case (1) : return cutTable(TableMap1366.SEAT_1);
            case (2) : return cutTable(TableMap1366.SEAT_2);
            case (3) : return cutTable(TableMap1366.SEAT_3);
            case (4) : return cutTable(TableMap1366.SEAT_4);
            case (5) : return cutTable(TableMap1366.SEAT_5);
            case (6) : return cutTable(TableMap1366.SEAT_6);
            case (7) : return cutTable(TableMap1366.SEAT_7);
            case (8) : return cutTable(TableMap1366.SEAT_8);
            default: return null;
        }
    }

    @Override
    public BufferedImage getActivePlayerImg(int i) {
        switch (i) {
            case (1) : return cutTable(TableMap1366.ACTIVE_SEAT_1);
            case (2) : return cutTable(TableMap1366.ACTIVE_SEAT_2);
            case (3) : return cutTable(TableMap1366.ACTIVE_SEAT_3);
            case (4) : return cutTable(TableMap1366.ACTIVE_SEAT_4);
            case (5) : return cutTable(TableMap1366.ACTIVE_SEAT_5);
            case (6) : return cutTable(TableMap1366.ACTIVE_SEAT_6);
            case (7) : return cutTable(TableMap1366.ACTIVE_SEAT_7);
            case (8) : return cutTable(TableMap1366.ACTIVE_SEAT_8);
            default: return null;
        }
    }

    @Override
    public BufferedImage getBetSizeImg(int i) {
        switch (i) {
            case (0) : return findBetImg(cutTable(TableMap1366.BET_SEAT_0));
            case (1) : return findBetImg(cutTable(TableMap1366.BET_SEAT_1));
            case (2) : return findBetImg(cutTable(TableMap1366.BET_SEAT_2));
            case (3) : return findBetImg(cutTable(TableMap1366.BET_SEAT_3));
            case (4) : return findBetImg(cutTable(TableMap1366.BET_SEAT_4));
            case (5) : return findBetImg(cutTable(TableMap1366.BET_SEAT_5));
            case (6) : return findBetImg(cutTable(TableMap1366.BET_SEAT_6));
            case (7) : return findBetImg(cutTable(TableMap1366.BET_SEAT_7));
            case (8) : return findBetImg(cutTable(TableMap1366.BET_SEAT_8));
            default: return null;
        }
    }

    @Override
    public BufferedImage getStackSizeImg(int i) {
        switch (i) {
            case (0) : return cutTable(TableMap1366.STACK_SEAT_0);
            case (1) : return cutTable(TableMap1366.STACK_SEAT_1);
            case (2) : return cutTable(TableMap1366.STACK_SEAT_2);
            case (3) : return cutTable(TableMap1366.STACK_SEAT_3);
            case (4) : return cutTable(TableMap1366.STACK_SEAT_4);
            case (5) : return cutTable(TableMap1366.STACK_SEAT_5);
            case (6) : return cutTable(TableMap1366.STACK_SEAT_6);
            case (7) : return cutTable(TableMap1366.STACK_SEAT_7);
            case (8) : return cutTable(TableMap1366.STACK_SEAT_8);
            default: return null;
        }
    }

    @Override
    public BufferedImage getDealerImg(int i) {
        switch (i) {
            case (0) : return cutTable(TableMap1366.DEALER_SEAT_0);
            case (1) : return cutTable(TableMap1366.DEALER_SEAT_1);
            case (2) : return cutTable(TableMap1366.DEALER_SEAT_2);
            case (3) : return cutTable(TableMap1366.DEALER_SEAT_3);
            case (4) : return cutTable(TableMap1366.DEALER_SEAT_4);
            case (5) : return cutTable(TableMap1366.DEALER_SEAT_5);
            case (6) : return cutTable(TableMap1366.DEALER_SEAT_6);
            case (7) : return cutTable(TableMap1366.DEALER_SEAT_7);
            case (8) : return cutTable(TableMap1366.DEALER_SEAT_8);
            default: return null;
        }
    }

    @Override
    public BufferedImage getActionBtnImg(int i) {
        switch (i) {
            case (1) : return cutTable(TableMap1366.CAN_FOLD);
            case (2) : return cutTable(TableMap1366.CAN_CHECK_CALL);
            case (3) : return cutTable(TableMap1366.CAN_RAISE);
            default: return null;
        }
    }

    @Override
    public BufferedImage getPotImg() {
       return cutTable(TableMap1366.POT);
    }

    @Override
    public BufferedImage getLvlImg() {
        return cutTable(TableMap1366.LVL);
    }
}
