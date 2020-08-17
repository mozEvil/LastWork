package ru.mozevil.controller.parser;

import ru.mozevil.model.TableMap;

import java.awt.image.BufferedImage;

public class TableCutter {

    private BufferedImage imageTable;

    public void setImageTable(BufferedImage imageTable) {
        this.imageTable = imageTable;
    }

    public BufferedImage getHandCardImg(int i) {
        switch (i) {
            case (1) : return cutTable(TableMap.HAND_CARD_1);
            case (2) : return cutTable(TableMap.HAND_CARD_2);
            default: return null;
        }
    }

    public BufferedImage getTableCardImg(int i) {
        switch (i) {
            case (1) : return cutTable(TableMap.TABLE_CARD_1);
            case (2) : return cutTable(TableMap.TABLE_CARD_2);
            case (3) : return cutTable(TableMap.TABLE_CARD_3);
            case (4) : return cutTable(TableMap.TABLE_CARD_4);
            case (5) : return cutTable(TableMap.TABLE_CARD_5);
            default: return null;
        }
    }

    public BufferedImage getSeatImg(int i) {
        switch (i) {
            case (1) : return cutTable(TableMap.SEAT_1);
            case (2) : return cutTable(TableMap.SEAT_2);
            case (3) : return cutTable(TableMap.SEAT_3);
            case (4) : return cutTable(TableMap.SEAT_4);
            case (5) : return cutTable(TableMap.SEAT_5);
            case (6) : return cutTable(TableMap.SEAT_6);
            case (7) : return cutTable(TableMap.SEAT_7);
            case (8) : return cutTable(TableMap.SEAT_8);
            default: return null;
        }
    }

    public BufferedImage getActivePlayerImg(int i) {
        switch (i) {
            case (1) : return cutTable(TableMap.ACTIVE_SEAT_1);
            case (2) : return cutTable(TableMap.ACTIVE_SEAT_2);
            case (3) : return cutTable(TableMap.ACTIVE_SEAT_3);
            case (4) : return cutTable(TableMap.ACTIVE_SEAT_4);
            case (5) : return cutTable(TableMap.ACTIVE_SEAT_5);
            case (6) : return cutTable(TableMap.ACTIVE_SEAT_6);
            case (7) : return cutTable(TableMap.ACTIVE_SEAT_7);
            case (8) : return cutTable(TableMap.ACTIVE_SEAT_8);
            default: return null;
        }
    }

    public BufferedImage getBetSizeFullImg(int i) {
        switch (i) {
            case (0) : return cutTable(TableMap.BET_SEAT_0);
            case (1) : return cutTable(TableMap.BET_SEAT_1);
            case (2) : return cutTable(TableMap.BET_SEAT_2);
            case (3) : return cutTable(TableMap.BET_SEAT_3);
            case (4) : return cutTable(TableMap.BET_SEAT_4);
            case (5) : return cutTable(TableMap.BET_SEAT_5);
            case (6) : return cutTable(TableMap.BET_SEAT_6);
            case (7) : return cutTable(TableMap.BET_SEAT_7);
            case (8) : return cutTable(TableMap.BET_SEAT_8);
            default: return null;
        }
    }

    public BufferedImage getBetSizeImg(int i) {
        switch (i) {
            case (0) : return findBetImg(cutTable(TableMap.BET_SEAT_0));
            case (1) : return findBetImg(cutTable(TableMap.BET_SEAT_1));
            case (2) : return findBetImg(cutTable(TableMap.BET_SEAT_2));
            case (3) : return findBetImg(cutTable(TableMap.BET_SEAT_3));
            case (4) : return findBetImg(cutTable(TableMap.BET_SEAT_4));
            case (5) : return findBetImg(cutTable(TableMap.BET_SEAT_5));
            case (6) : return findBetImg(cutTable(TableMap.BET_SEAT_6));
            case (7) : return findBetImg(cutTable(TableMap.BET_SEAT_7));
            case (8) : return findBetImg(cutTable(TableMap.BET_SEAT_8));
            default: return null;
        }
    }

    public BufferedImage findBetImg(BufferedImage image) {

        BufferedImage img = image;

        int[] betFieldRBG = new int[] {
                -16688868, -16688867, -16688355, -16688612, -16688611, -12940717, -16689124, -12940973,
                -16689380, -16689636, -12941230, -16691687, -16689893, -16690406, -16690406, -16690149,
                -12941742, -16691943, -16690662, -16693481, -16691431, -16691175
        };

        boolean stopFind = false;

        for (int i = 0; i < img.getWidth(); i++) {
            int  rbg = img.getRGB(i, 9);
            for (int value : betFieldRBG) {
                if (value == rbg) {
                    img = img.getSubimage(i, 0, img.getWidth() - i, img.getHeight());
                    stopFind = true;
                    break;
                }
            }
            if (stopFind) {
                stopFind = false;
                break;
            }
        }

        for (int i = img.getWidth()-1; i > 0; i--) {
            int  rbg = img.getRGB(i, 9);
            for (int value : betFieldRBG) {
                if (value == rbg) {
                    img = img.getSubimage(0, 0, i+1, img.getHeight());
                    stopFind = true;
                    break;
                }
            }
            if (stopFind) break;
        }

        return img;
    }

    public BufferedImage getStackSizeImg(int i) {
        switch (i) {
            case (0) : return cutTable(TableMap.STACK_SEAT_0);
            case (1) : return cutTable(TableMap.STACK_SEAT_1);
            case (2) : return cutTable(TableMap.STACK_SEAT_2);
            case (3) : return cutTable(TableMap.STACK_SEAT_3);
            case (4) : return cutTable(TableMap.STACK_SEAT_4);
            case (5) : return cutTable(TableMap.STACK_SEAT_5);
            case (6) : return cutTable(TableMap.STACK_SEAT_6);
            case (7) : return cutTable(TableMap.STACK_SEAT_7);
            case (8) : return cutTable(TableMap.STACK_SEAT_8);
            default: return null;
        }
    }

    public BufferedImage getDealerImg(int i) {
        switch (i) {
            case (0) : return cutTable(TableMap.DEALER_SEAT_0);
            case (1) : return cutTable(TableMap.DEALER_SEAT_1);
            case (2) : return cutTable(TableMap.DEALER_SEAT_2);
            case (3) : return cutTable(TableMap.DEALER_SEAT_3);
            case (4) : return cutTable(TableMap.DEALER_SEAT_4);
            case (5) : return cutTable(TableMap.DEALER_SEAT_5);
            case (6) : return cutTable(TableMap.DEALER_SEAT_6);
            case (7) : return cutTable(TableMap.DEALER_SEAT_7);
            case (8) : return cutTable(TableMap.DEALER_SEAT_8);
            default: return null;
        }
    }

    public BufferedImage getActionBtnImg(int i) {
        switch (i) {
            case (1) : return cutTable(TableMap.CAN_FOLD);
            case (2) : return cutTable(TableMap.CAN_CHECK_CALL);
            case (3) : return cutTable(TableMap.CAN_RAISE);
            default: return null;
        }
    }

    public BufferedImage getPotImg() {
       return cutTable(TableMap.POT);
    }


    private BufferedImage cutTable(TableMap tableMap) {

        if (imageTable == null) return null;

        return imageTable.getSubimage(tableMap.getX(), tableMap.getY(), tableMap.getW(), tableMap.getH());
    }

    public BufferedImage getLvlImg() {
        return cutTable(TableMap.LVL);
    }
}
