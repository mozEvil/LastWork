package ru.mozevil.controller.strategy;

import ru.mozevil.model.*;
import ru.mozevil.model.positions.Position;

public class SnG_9max implements PokerStrategy {

    private final Range range_early_open_UTG = new Range("TT+, AK");
    private final Range range_early_open_MP = new Range("88+, AQ+");
    private final Range range_early_open_HJ_CO = new Range("66+, AT+, KQ");
    private final Range range_early_open_BTN_SB = new Range("44+, A2s+, A7o+, JT++");

    private final Range range_early_isolate_UTG = new Range("TT+, AK");
    private final Range range_early_isolate_MP = new Range("88+, AQ+");
    private final Range range_early_isolate_HJ_CO_BTN_SB_BB = new Range("88+, AT+, KQ");

    private final Range range_early_overlimp_CO_BTN = new Range("22-77, A2s-A9s, 54s-QJs");
    private final Range range_early_overlimp_SB = new Range("22-77, A2s-A9s, 54s-QJs, J9s-KJs, KTs");

    private final Range range_early_reraise = new Range("QQ+, AK");
    private final Range range_early_reraise_coldcall = new Range("TT-JJ, AQ");
    private final Range range_early_coldcall = new Range("22-99, A2s-A9s, AT-AJ, 54s-KQs, QTs-KJs, KTs");

    private final Range range_early_bb_vs_sb_raise = new Range("22+, A2+, JT++");
    private final Range range_early_bb_vs_sb_limp = new Range("66+, A7o+, A2s+, QJo++, JTs++");


    @Override
    public Decision makeDecision(Environment env) {

        Street street = env.getStreet();

        if (street == null) return new Decision(Move.FOLD);

        if (street == Street.PREFLOP) return makePreflop(env);

        return makePostflop(env);
    }

    private Decision makePreflop(Environment env) {
        //TODO
        // при стеке > 20bb открываться по чарту раней стадии

        double heroStack = env.getHeroStackSize();
        int lvl = env.getLvlBlinds();

        // если ранняя стадия и стек > 20bb то играем по чарту ранней стадии
        if (heroStack > 20 && lvl <= 3) return makePreflop_earlyStage(env);


        return new Decision(Move.FOLD);
    }

    //TODO
    private Decision makePreflop_earlyStage(Environment env) {
        // open-raise
        //UTG - TT+, AK
        //MP - 88+ AQ+
        //HJ,CO - 66+, AT+, KQ
        //BTN,SB - 44+, A2s+, A7o+, JT++

        // узнать нашу позицию
        Position position = env.getHeroPosition();

        // узнать нашу стартовую руку
        Hand hand = env.getHeroHand();


        // опен рейз
        if (env.isOpenRaise()) {
            boolean canRaise = false;

            // проверить входит ли наша рука в диапазон рейза из текущей позиции
            switch (position) {
                case EP:
                    canRaise = range_early_open_UTG.containsHand(hand);
                    break;
                case MP:
                    canRaise = range_early_open_MP.containsHand(hand);
                    break;
                case HJ:
                case CO:
                    canRaise = range_early_open_HJ_CO.containsHand(hand);
                    break;
                case BTN:
                case SB:
                    canRaise = range_early_open_BTN_SB.containsHand(hand);
                    break;
            }

            if (canRaise) return DF.openRaise(env.getLvlBlinds());
        }

        // оверлимп/изолейт
        if (env.isLimpedPot()) {
            //изолейт
            boolean isolate = false;

            switch (position) {
                case EP:
                    isolate = range_early_isolate_UTG.containsHand(hand);
                    break;
                case MP:
                    isolate = range_early_isolate_MP.containsHand(hand);
                    break;
                case HJ:
                case CO:
                case BTN:
                case SB:
                case BB:
                    isolate = range_early_isolate_HJ_CO_BTN_SB_BB.containsHand(hand);
                    break;
            }

            if (isolate) return DF.isolateRaise(env.getCountLimps());

            // оверлимп
            boolean overlimp = false;

            switch (position) {
                case CO:
                case BTN:
                    overlimp = range_early_overlimp_CO_BTN.containsHand(hand);
                    break;
                case SB:
                    overlimp = range_early_overlimp_SB.containsHand(hand);
                    break;
            }

            if (overlimp) return DF.call();
        }

        // 3 бет
        if (env.isRaisedPot()) {
            boolean reraise = range_early_reraise.containsHand(hand);
            if (reraise) return DF.reraise(env.getMaxBetSize(), env.getCountColdCall());

            //TODO условия на рейз или колд-колл
            boolean reraise_coldcall = range_early_reraise_coldcall.containsHand(hand);
            if (reraise_coldcall) return DF.call();

            //TODO условие на размер ставки не более 5% от эфф.стека
            boolean coldcall = range_early_coldcall.containsHand(hand);
            if (coldcall) return DF.call();
        }

        // TODO SB (нужна статистика на игроков)

        // TODO BB

        return new Decision(Move.FOLD);
    }


    //TODO
    private Decision makePostflop(Environment env) {


        return new Decision();
    }

}
