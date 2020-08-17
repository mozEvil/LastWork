package ru.mozevil.controller.strategy;

import org.apache.log4j.Logger;
import ru.mozevil.model.*;
import ru.mozevil.model.positions.Position;

public class SnG_45_simple implements PokerStrategy {

    private static final Logger log = Logger.getLogger(SnG_45_simple.class.getName());

    private Environment env;

//    private final Level lvl = LVL_Factory.getLVL_SnG_45();
    private final Level lvl = LVL_Factory.getLVL_SnG_9_PlayMoney();

    private final Chart chartNash_push_HU = ChartFactory.getChart_Nash_push_HU();
    private final Chart chartNash_call_HU = ChartFactory.getChart_Nash_call_HU();

    // >= 11
    private final Range range_EP_open = new Range("TT+, AK");
    private final Range range_MP_open = new Range("88+, AQ+");
    private final Range range_HJ_CO_open = new Range("66+, AT+, KQ");
    private final Range range_BTN_SB_open = new Range("44+, A2s+, A7o+, JT++");

    // >= 25
    private final Range range_EP_isolate = new Range("TT+, AK");
    private final Range range_MP_isolate = new Range("88+, AQ+");
    private final Range range_HJ_BB_isolate = new Range("88+, AT+, KQ");

    private final Range range_overlimp_CO_BTN = new Range("22-77, A2s-A9s, 54s-QJs");
    private final Range range_overlimp_SB = new Range("22-77, A2s-A9s, 54s-QJs, QTs, KTs, KJs");

    private final Range range_reraise = new Range("QQ+, AK");
    private final Range range_coldcall = new Range("TT-JJ, AQ");

    private final Range range_BB_cc_vs_steal = new Range("22+, A2+, JT++"); // steal from SB,BTN
    private final Range range_BB_raise_vs_SB_complete = new Range("66+, A7+, A2s+, QJ++, JTs++");

    // < 25
    private final Range range_resteal = new Range("88+, AQ+");

    // < 11
    private final Range range_EP_MP_openPush_10bb   = new Range("99+, AK, AQs");
    private final Range range_EP_MP_openPush_8bb    = new Range("88+, AQ+, AJs");
    private final Range range_EP_MP_openPush_6_5bb  = new Range("77+, AJ+, ATs");
    private final Range range_EP_MP_openPush_5bb    = new Range("55+, AT+, A8s+, KQ, KTs+, QJs");

    private final Range range_HJ_openPush_10bb  = new Range("88+, AQ+, AJs");
    private final Range range_HJ_openPush_8bb   = new Range("66+, AT+, A9s, KQs");
    private final Range range_HJ_openPush_6_5bb = new Range("44+, A9+, A5s+, KQ, JTs++");
    private final Range range_HJ_openPush_5bb  = new Range("22+, A4+, A2s+, JT++, K6s+, Q8s+, J8s+");

    private final Range range_CO_openPush_10bb  = new Range("66+, AT+, KQs");
    private final Range range_CO_openPush_8bb   = new Range("33+, A9+, A4s+, KQ, JTs++, J9s, T9s");
    private final Range range_CO_openPush_6_5bb = new Range("22+, A4+, A2s+, JT++, K6s+, T8s++, 76s+, T7s, 97s");
    private final Range range_CO_openPush_5bb  = new Range("22+,A2+,K7+,K2s+,Q9+,Q4s+,J8+,J5s+,54s+,T8,T6s+,96s+,85s+,75s");

    private final Range range_BTN_openPush_10bb = new Range("33+, A8+, A4s+, KQ, JTs++, T9s");
    private final Range range_BTN_openPush_8bb = new Range("22+, A2+, JT++, K6s+, Q8s+, J7s+, T9, T7s+, 76s+,97s,86s");
    private final Range range_BTN_openPush_6_5bb = new Range("22+,A2+,K6+,Q2s++,J8++,J3s+,T7+,T4s+,97+,95s+,86+,84s+," +
            "76+,74s+,64s+,53s+");
    private final Range range_BTN_openPush_5bb = new Range("22+, 32s++, J2++, T3+, 94+, 84+, 74+, 63+, 53+, 43");

    private final Range range_SB_openPush_10bb = new Range("22+,A2+,K4+,Q5,86++,92s++,84s+,75+,73s+,65,63s+,52s+,43s");
    private final Range range_SB_openPush_8bb = new Range("22+, 32s++, 82++, 73+, 62+, 52+, 42+");
    private final Range range_SB_openPush_6_5bb = new Range("All");
    private final Range range_SB_openPush_5bb = new Range("All");

    private final Range range_BB_cc_vs_EP_MP_push_10bb = new Range("JJ+, AK");
    private final Range range_BB_cc_vs_EP_MP_push_7_5bb = new Range("77+, AQ+, AJs");
    private final Range range_BB_cc_vs_EP_MP_push_5bb = new Range("33+, A9+, A8s, KQs");

    private final Range range_BB_cc_vs_HJ_CO_push_10bb = new Range("88+, AQ+");
    private final Range range_BB_cc_vs_HJ_CO_push_7_5bb = new Range("55+, AT+, A9s");
    private final Range range_BB_cc_vs_HJ_CO_push_5bb = new Range("22+, A7+, A4s+, KQ, JTs++");

    private final Range range_BB_cc_vs_SB_BTN_push_10bb = new Range("66+, AJ+, ATs");
    private final Range range_BB_cc_vs_SB_BTN_push_7_5bb = new Range("44+, A8+, A7s+");
    private final Range range_BB_cc_vs_SB_BTN_push_5bb = new Range("22+, A2+, KT+, JTs++");

    @Override
    public void makeDecision(Environment env) {

        this.env = env;
        env.setLvl(lvl);

        if (env.getStreet() == null) {
            log.error("Street == null");
            env.setDecision(DF.check_fold());
            return;
        }

        if (env.getStreet() == Street.PREFLOP) {
            env.setDecision(makePreflop());
        } else {
            env.setDecision(makePostflop());
        }
    }

    private Decision makePreflop() {

        double realHeroStack = env.getRealHeroStackSize();

        // хедз-ап
        if (env.isHeadsUp()) {
            return makePreflop_HU();
        }

        // не хедз-ап, первый раунд торгов
        if (env.isFirstPreflopRound()) {

            if (realHeroStack >= 25) return makePreflop_early();

            if (realHeroStack < 25 && realHeroStack >= 11) return makePreflop_mid();

            // < 11 bb
            return makePreflop_late();

        } else {
            // последующие раунды торговли // озночает, что после хода хиро был ререйз
            //TODO проверить как работают хот-кеи в ситуации когда не хватает фишек на ререйз и можно только колить
            if (range_reraise.containsHand(env.getHeroHand())) return DF.push();

            if (env.isAllIn() && env.getPotOdds() < 15) return DF.call();

            // в иных случая
            return DF.fold();
        }
    }

    private Decision makePreflop_HU() {

        Hand heroHand = env.getHeroHand();
        Position heroPos = env.getHeroPosition();
        double effStack = env.getRealEffectiveStackSize();
        boolean canPush = false;

        switch (heroPos) {
            case SB: canPush = chartNash_push_HU.isCanPush(heroHand, effStack); break; // BTN
            case BB: canPush = chartNash_call_HU.isCanPush(heroHand, effStack); break;
        }

        if (canPush) return DF.push();

        return DF.check_fold();
    }

    private Decision makePreflop_early() {

        // хиро на BB vs SB комплита, все остальные фолд
        if (env.isPreflopBBvsSBcomplete()) return PEM_BB_vs_SB_complete();

        // хиро на BB vs стила с SB/BTN, все остальные фолд
        if (env.isPreflopBBvsSteal()) return PE_BB_vs_Steal();

        // до хиро все фолд
        if (env.isOpenRaise()) return PEM_OpenRaise();

        // до хиро был лимп
        if (env.isLimpedPot()) return PE_LimpedPot();

        // до хиро был рейз
        if (env.isRaisedPot()) return PE_RaisedPot();

        // до хиро был рейз и ререйз
        if (env.is3BetPot()) return PE_3betPot();

        // хз что произошло
        log.info("UNKNOWN SITUATION ON PREFLOP EARLY");
        return DF.check_fold();
    }

    private Decision PE_3betPot() {

        if (range_reraise.containsHand(env.getHeroHand())) return DF.push();

        return DF.fold();
    }

    private Decision PE_RaisedPot() {

        Hand heroHand = env.getHeroHand();
        double maxBet = env.getMaxBetSize();

        if (range_reraise.containsHand(heroHand)) return DF.push();

        if (range_coldcall.containsHand(heroHand) && maxBet <= 3) return DF.call();

        return DF.fold();
    }

    private Decision PE_BB_vs_Steal() {

        Hand heroHand = env.getHeroHand();
        double effStack = env.getRealEffectiveStackSize();
        double maxBet = env.getMaxBetSize();

        if (effStack > 15) {
            if (range_reraise.containsHand(heroHand)) return DF.reraise(maxBet, env.getCountColdCall());
            if (maxBet <= 3 && range_BB_cc_vs_steal.containsHand(heroHand)) return DF.call();

        } else {
            if (range_resteal.containsHand(heroHand)) return DF.push();
        }

        return DF.fold();
    }

    private Decision PE_LimpedPot() {

        Hand heroHand = env.getHeroHand();
        Position heroPos = env.getHeroPosition();
        int countLimps = env.getCountLimps();

        boolean canIsolate;

        switch (heroPos) {
            case EP: canIsolate = range_EP_isolate.containsHand(heroHand); break;
            case MP: canIsolate = range_MP_isolate.containsHand(heroHand); break;
            default: canIsolate = range_HJ_BB_isolate.containsHand(heroHand); break;
        }

        if (canIsolate) return DF.isolateRaise(countLimps);

        if (heroPos == Position.CO || heroPos == Position.BTN) {
            if (range_overlimp_CO_BTN.containsHand(heroHand)) return DF.call();
        }

        if (heroPos == Position.SB) {
            if (range_overlimp_SB.containsHand(heroHand)) return DF.call();
        }

        return DF.check_fold();
    }

    private Decision makePreflop_mid() {

        // до хиро все фолд
        if (env.isOpenRaise()) return PEM_OpenRaise();

        // хиро на BB vs SB комплита, все остальные фолд
        if (env.isPreflopBBvsSBcomplete()) return PEM_BB_vs_SB_complete();

        // пушим с монстрами
        if (range_reraise.containsHand(env.getHeroHand())) return DF.push();

        // magic resteal, если в поте больше 20% от стека
        if (env.getPercentOfHeroStackInThePot() >= 20
                && range_resteal.containsHand(env.getHeroHand())) return DF.push();

        // в остальных случаях
        return DF.check_fold();
    }

    private Decision PEM_BB_vs_SB_complete() {

        Hand heroHand = env.getHeroHand();

        if (range_BB_raise_vs_SB_complete.containsHand(heroHand)) return DF.openRaise(1);

        // в остальных случаях
        return DF.check_fold();
    }

    private Decision PEM_OpenRaise() {

        Position heroPosition = env.getHeroPosition();
        Hand heroHand = env.getHeroHand();

        boolean canOpen = false;

        switch (heroPosition) {
            case EP: canOpen = range_EP_open.containsHand(heroHand); break;
            case MP: canOpen = range_MP_open.containsHand(heroHand); break;
            case HJ:
            case CO: canOpen = range_HJ_CO_open.containsHand(heroHand); break;
            case BTN:
            case SB: canOpen = range_BTN_SB_open.containsHand(heroHand); break;
        }

        if (canOpen) {
            if (heroPosition == Position.SB) return DF.openRaiseSB();
            return DF.openRaise(env.getLvlBlinds());
        }
        return DF.fold();
    }

    private Decision makePreflop_late() {

        // с монстрами в любой ситуации пуш
        if (range_reraise.containsHand(env.getHeroHand())) return DF.push();

        // до хиро все фолд
        if (env.isOpenRaise()) return PL_pushFold();

        // хиро на BB vs SB комплит, все остальные фолд
        if (env.isPreflopBBvsSBcomplete()) return DF.push();

        // хиро на ББ и был пуш
        if (env.isPreflopBBvsOpenPush()) return PL_BB_vs_push();

        // magic resteal
        if (range_resteal.containsHand(env.getHeroHand())) return DF.push();

        // в остальных случаях
        return DF.check_fold();
    }

    private Decision PL_BB_vs_push() {

        double realEffStackOfPusher = env.getRealEffectiveStackSizeForTarget(env.getOpenPusherWhenHeroOnBB());

        if (realEffStackOfPusher <= 2) return DF.call();

        if (realEffStackOfPusher < 5.5) return BB_callPush_5bb();
        if (realEffStackOfPusher < 8) return BB_callPush_7_5bb();
        return BB_callPush_10bb();
    }

    private Decision BB_callPush_5bb() {

        Position pusherPos = env.getPositionOfOpenPusher();
        Hand heroHand = env.getHeroHand();

        if (pusherPos == null) {
            log.error("ERROR BB_callPush_5bb");
            return DF.fold();
        }

        boolean canCallPush = false;

        switch (pusherPos) {
            case EP:
            case MP: canCallPush = range_BB_cc_vs_EP_MP_push_5bb.containsHand(heroHand); break;
            case HJ:
            case CO: canCallPush = range_BB_cc_vs_HJ_CO_push_5bb.containsHand(heroHand); break;
            case BTN:
            case SB: canCallPush = range_BB_cc_vs_SB_BTN_push_5bb.containsHand(heroHand); break;
        }

        if (canCallPush) return DF.push();

        return DF.fold();
    }

    private Decision BB_callPush_7_5bb() {

        Position pusherPos = env.getPositionOfOpenPusher();
        Hand heroHand = env.getHeroHand();

        if (pusherPos == null) {
            log.error("ERROR BB_callPush_7_5bb");
            return DF.fold();
        }

        boolean canCallPush = false;

        switch (pusherPos) {
            case EP:
            case MP: canCallPush = range_BB_cc_vs_EP_MP_push_7_5bb.containsHand(heroHand); break;
            case HJ:
            case CO: canCallPush = range_BB_cc_vs_HJ_CO_push_7_5bb.containsHand(heroHand); break;
            case BTN:
            case SB: canCallPush = range_BB_cc_vs_SB_BTN_push_7_5bb.containsHand(heroHand); break;
        }

        if (canCallPush) return DF.push();

        return DF.fold();
    }

    private Decision BB_callPush_10bb() {

        Position pusherPos = env.getPositionOfOpenPusher();
        Hand heroHand = env.getHeroHand();

        if (pusherPos == null) {
            log.error("ERROR BB_callPush_10bb");
            return DF.fold();
        }

        boolean canCallPush = false;

        switch (pusherPos) {
            case EP:
            case MP: canCallPush = range_BB_cc_vs_EP_MP_push_10bb.containsHand(heroHand); break;
            case HJ:
            case CO: canCallPush = range_BB_cc_vs_HJ_CO_push_10bb.containsHand(heroHand); break;
            case BTN:
            case SB: canCallPush = range_BB_cc_vs_SB_BTN_push_10bb.containsHand(heroHand); break;
        }

        if (canCallPush) return DF.push();

        return DF.fold();
    }

    private Decision PL_pushFold() {

        double realHeroStack = env.getRealHeroStackSize();  // < 11 bb

        if (realHeroStack < 3) return DF.push();      // < 3 bb push any two
        if (realHeroStack < 5.5) return pushFold_5bb(); // 3 - 5.49
        if (realHeroStack < 7) return pushFold_6_5bb(); // 5.5 - 6.99
        if (realHeroStack < 8.5) return pushFold_8bb(); // 7 - 8.49

        return pushFold_10bb();     // 8.5 - 10.99
    }

    private Decision pushFold_5bb() {

        Position heroPosition = env.getHeroPosition();
        Hand heroHand = env.getHeroHand();

        boolean canPush = false;

        switch (heroPosition) {
            case EP:
            case MP: canPush = range_EP_MP_openPush_5bb.containsHand(heroHand); break;
            case HJ: canPush = range_HJ_openPush_5bb.containsHand(heroHand); break;
            case CO: canPush = range_CO_openPush_5bb.containsHand(heroHand); break;
            case BTN: canPush = range_BTN_openPush_5bb.containsHand(heroHand); break;
            case SB: canPush = range_SB_openPush_5bb.containsHand(heroHand); break;
        }

        if (canPush) return DF.push();

        return DF.fold();
    }

    private Decision pushFold_6_5bb() {

        Position heroPosition = env.getHeroPosition();
        Hand heroHand = env.getHeroHand();

        boolean canPush = false;

        switch (heroPosition) {
            case EP:
            case MP: canPush = range_EP_MP_openPush_6_5bb.containsHand(heroHand); break;
            case HJ: canPush = range_HJ_openPush_6_5bb.containsHand(heroHand); break;
            case CO: canPush = range_CO_openPush_6_5bb.containsHand(heroHand); break;
            case BTN: canPush = range_BTN_openPush_6_5bb.containsHand(heroHand); break;
            case SB: canPush = range_SB_openPush_6_5bb.containsHand(heroHand); break;
        }

        if (canPush) return DF.push();

        return DF.fold();
    }

    private Decision pushFold_8bb() {

        Position heroPosition = env.getHeroPosition();
        Hand heroHand = env.getHeroHand();

        boolean canPush = false;

        switch (heroPosition) {
            case EP:
            case MP: canPush = range_EP_MP_openPush_8bb.containsHand(heroHand); break;
            case HJ: canPush = range_HJ_openPush_8bb.containsHand(heroHand); break;
            case CO: canPush = range_CO_openPush_8bb.containsHand(heroHand); break;
            case BTN: canPush = range_BTN_openPush_8bb.containsHand(heroHand); break;
            case SB: canPush = range_SB_openPush_8bb.containsHand(heroHand); break;
        }

        if (canPush) return DF.push();

        return DF.fold();
    }

    private Decision pushFold_10bb() {

        Position heroPosition = env.getHeroPosition();
        Hand heroHand = env.getHeroHand();

        boolean canPush = false;

        switch (heroPosition) {
            case EP:
            case MP: canPush = range_EP_MP_openPush_10bb.containsHand(heroHand); break;
            case HJ: canPush = range_HJ_openPush_10bb.containsHand(heroHand); break;
            case CO: canPush = range_CO_openPush_10bb.containsHand(heroHand); break;
            case BTN: canPush = range_BTN_openPush_10bb.containsHand(heroHand); break;
            case SB: canPush = range_SB_openPush_10bb.containsHand(heroHand); break;
        }

        if (canPush) return DF.push();

        return DF.fold();
    }

    private Decision makePostflop() {

        if (env.getStreet() == Street.FLOP) return makeFlop();
        if (env.getStreet() == Street.TURN) return makeTurn();
        if (env.getStreet() == Street.RIVER) return makeRiver();

        //история действий на предыдущих улицах !?
        //линия поведения !?

        // что-то пошло не так
        log.error("ERROR POSTFLOP");
        return DF.check_fold();
    }

    private Decision makeFlop() {

//        if (env.isMultipot()) {
//            if (env.getHeroIOP() == IOP.IN_POSITION) return flopMulti_IP();
//            if (env.getHeroIOP() == IOP.OUT_OF_POSITION) return flopMulti_OOP();
//        } else {
//            if (env.getHeroIOP() == IOP.IN_POSITION) return flopSingle_IP();
//            if (env.getHeroIOP() == IOP.OUT_OF_POSITION) return flopSingle_OOP();
//        }
//
//        // что-то пошло не так
//        log.error("ERROR FLOP");
        return DF.check_fold();
    }

    private Decision flopSingle_IP() {
        //TODO
        // нужен класс хранящий историю дейсвтий в текущей раздаче на текущем столе,
        // для этого нужно идентифицировать стол, для этого нужно дописать OCR парсер (id стола, имена оппов)
        // и возможно написать парсер истории рук

        // раунд торговли
        // у кого инициатива?
        // действие оппа ? донк|бет|чек|чек-рейз|рейз-ререйз
        // если был бет, то какой сайзинг
        // сила нашей руки
        // действие


        HandType handType = env.getHeroHandType();



        // что-то пошло не так
        log.error("ERROR flopSingle_IP");
        return DF.check_fold();
    }

    private Decision makeTurn() {
//
//        if (env.isMultipot()) {
//            if (env.getHeroIOP() == IOP.IN_POSITION) return turnMulti_IP();
//            if (env.getHeroIOP() == IOP.OUT_OF_POSITION) return turnMulti_OOP();
//        } else {
//            if (env.getHeroIOP() == IOP.IN_POSITION) return turnSingle_IP();
//            if (env.getHeroIOP() == IOP.OUT_OF_POSITION) return turnSingle_OOP();
//        }

        // что-то пошло не так
        log.error("ERROR TURN");
        return DF.check_fold();
    }

    private Decision makeRiver() {

//        if (env.isMultipot()) {
//            if (env.getHeroIOP() == IOP.IN_POSITION) return riverMulti_IP();
//            if (env.getHeroIOP() == IOP.OUT_OF_POSITION) return riverMulti_OOP();
//        } else {
//            if (env.getHeroIOP() == IOP.IN_POSITION) return riverSingle_IP();
//            if (env.getHeroIOP() == IOP.OUT_OF_POSITION) return riverSingle_OOP();
//        }

        // что-то пошло не так
        log.error("ERROR RIVER");
        return DF.check_fold();
    }


}
