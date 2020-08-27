package ru.mozevil.controller.parser;

import ru.mozevil.controller.parser.cutter.Cutter;
import ru.mozevil.controller.parser.cutter.TableCutter1024;
import ru.mozevil.model.*;
import ru.mozevil.model.factory.HANDS;
import ru.mozevil.model.positions.*;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class TableParser implements PokerParser {

    private static final int MAX_TABLE_SIZE = 9;

    private Cutter cutter = new TableCutter1024();
    private CompareParser parser = new CompareParser();
    private OCRParser ocr = new OCRParser();

    @Override
    public void setImageTable(BufferedImage imageTable) {
       cutter.setImageTable(imageTable);
    }

    @Override
    public boolean isAction() {
        return parser.isAction(cutter.getActionBtnImg(3));
    }

    @Override
    public Environment parseTable() {
        // инициализация значениями по умолчанию
        Seat[] seats = new Seat[MAX_TABLE_SIZE];
        for (int i = 0; i < MAX_TABLE_SIZE; i++) {
            seats[i] = new Seat();
            seats[i].setPlayer(new Player());
        }

        seats[0].setEmpty(false); // место hero не может быть пустым
        seats[0].getPlayer().setActive(true);  // hero всегда активный

        for (int i = 1; i < MAX_TABLE_SIZE; i++) {
            // парсим пустые места за столом
            seats[i].setEmpty(parser.isEmptySeat(cutter.getSeatImg(i)));
            // парсим активность игроков
            seats[i].getPlayer().setActive(parser.isActivePlayer(cutter.getActivePlayerImg(i)));
        }
        // считаем количество игроков за столом, чтобы знать какой PositionAct использовать
        int countPlayers = (int) Arrays.stream(seats).filter(seat -> !seat.isEmpty()).count();

        // определяем место дилера, присваиваем игроку на этом месте активный номер дилера
        int dealer = getDealerSeatNumber();
        ActionNumber dealerAct = getDealerAct(countPlayers);
        seats[dealer].getPlayer().setAct(dealerAct);

        // присваиваем активные номера оставшимся игрокам
        int index = dealer;
        ActionNumber act = dealerAct;
        for (int i = 0; i < MAX_TABLE_SIZE - 1; i++) {
            index = nextSeatIndex(index);
            if (!seats[index].isEmpty()) {
                act = act.nextAct();
                seats[index].getPlayer().setAct(act);
            }
        }
        // парсим руку hero
        seats[0].getPlayer().setHand(getHand());

        // парсим общие карты
        Card[] tableCards = getTableCards();

        // парсим возможные варианты действий
        //fixme нигде не используются, подумать об их надобности
        boolean canCheckCall = parser.isAction(cutter.getActionBtnImg(2));
        boolean canFold = parser.isAction(cutter.getActionBtnImg(1));

        // парсим текущий уровень блаиндов
        int lvl = ocr.parseLevel(cutter.getLvlImg());
//        int lvl = 1;

        // парсим размер банка
        double potSize = ocr.parsePot(cutter.getPotImg());

        for (int i = 0; i < MAX_TABLE_SIZE; i++) {
            if (!seats[i].isEmpty()) {
                // парсим стеки и ставки всех игроков
                seats[i].getPlayer().setStackSize(ocr.parseStack(cutter.getStackSizeImg(i)));
                seats[i].getPlayer().setBetSize(ocr.parseBet(cutter.getBetSizeImg(i)));

                // парсим стеки и ставки только активных игроков
//                if (seats[i].getPlayer().isActive()) {
//                    seats[i].getPlayer().setStackSize(ocr.parseStack(cutter.getStackSizeImg(i)));
//                    seats[i].getPlayer().setBetSize(ocr.parseBet(cutter.getBetSizeImg(i)));
//                }
            }
        }

        // обновляем данные таблицы
        Table table = new Table(); // FIXME: 16.07.2020 не создавать новую таблицу, а доставать ее из пула по id
        table.setSeats(seats);
        table.setPotSize(potSize);
        table.setTableCards(tableCards);
        table.setLvl(lvl);
        table.setCanCheckCall(canCheckCall);
        table.setCanFold(canFold);

        return new Environment(table);
    }

    /**
     * -1 - нет соответствий
     * 0 - Hero
     * 1 - слева от Hero
     * 8 - справа от Hero
     * */
    private int getDealerSeatNumber() {
        for (int i = 0; i < MAX_TABLE_SIZE; i++) {
            if (parser.isDealer(cutter.getDealerImg(i))) return i;
        }
        return -1;
    }

    private ActionNumber getDealerAct(int countPlayers) {
        switch (countPlayers) {
            case (2) : return PositionAct_2pl.ACT_2;
            case (3) : return PositionAct_3pl.ACT_3;
            case (4) : return PositionAct_4pl.ACT_4;
            case (5) : return PositionAct_5pl.ACT_5;
            case (6) : return PositionAct_6pl.ACT_6;
            case (7) : return PositionAct_7pl.ACT_7;
            case (8) : return PositionAct_8pl.ACT_8;
            case (9) : return PositionAct_9pl.ACT_9;
        }
        return null;
    }

    private int nextSeatIndex(int i) {
        return i + 1 == MAX_TABLE_SIZE ? 0 : i + 1;
    }

    private Hand getHand() {
        Card card1 = parser.parseCard(cutter.getHandCardImg(1));
        Card card2 = parser.parseCard(cutter.getHandCardImg(2));

        return HANDS.GET.hand(card1, card2);
    }

    private Card[] getTableCards() {
        Card[] cards = new Card[5];
        cards[0] = parser.parseCard(cutter.getTableCardImg(1));
        cards[1] = parser.parseCard(cutter.getTableCardImg(2));
        cards[2] = parser.parseCard(cutter.getTableCardImg(3));
        cards[3] = parser.parseCard(cutter.getTableCardImg(4));
        cards[4] = parser.parseCard(cutter.getTableCardImg(5));

        return cards;
    }
}
