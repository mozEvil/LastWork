package ru.mozevil.model.factory;

import ru.mozevil.model.Bet;
import ru.mozevil.model.Decision;
import ru.mozevil.model.Move;

/** Decision Factory*/
public class DF {
    private DF() {}

    public static Decision fold() {
        return new Decision();
    }

    public static Decision check() {
        return new Decision(Move.CHECK);
    }

    public static Decision call() {
        return new Decision(Move.CALL);
    }

    public static Decision check_fold() {
        return new Decision(Move.FOLD);
    }

    public static Decision check_call() {
        return new Decision(Move.CALL);
    }

    public static Decision raisePOT() {
        return new Decision(Move.RAISE, Bet.POT_100);
    }

    public static Decision push() {
        return new Decision(Move.RAISE, Bet.ALL_IN);
    }

    public static Decision openRaiseSB() {
        return new Decision(Move.RAISE, Bet.BB_3);
    }

    public static Decision openRaise(int lvl) {
        switch (lvl) {
            case 1 :
            case 2 : return new Decision(Move.RAISE, Bet.BB_3);
            case 3 : return new Decision(Move.RAISE, Bet.BB_2_5);
            case 4 : return new Decision(Move.RAISE, Bet.BB_2_25);
            default: return new Decision(Move.RAISE, Bet.BB_2);
        }
    }

    public static Decision isolateRaise(int count) {
        switch (count) {
            case 1 : return new Decision(Move.RAISE, Bet.BB_4);
            case 2 : return new Decision(Move.RAISE, Bet.BB_5);
            case 3 : return new Decision(Move.RAISE, Bet.BB_6);
            case 4 : return new Decision(Move.RAISE, Bet.BB_7);
            case 5 : return new Decision(Move.RAISE, Bet.BB_8);
            case 6 : return new Decision(Move.RAISE, Bet.BB_9);
            case 7 : return new Decision(Move.RAISE, Bet.BB_10);
            default: return new Decision(Move.RAISE, Bet.POT_100);
        }
    }

    public static Decision reraise(double maxBet, int countCallers) {
        return new Decision(Move.RAISE, getBetForReraise(maxBet, countCallers));
    }

    private static Bet getBetForReraise(double betSize, int count) {
        int reRaiseSize = (int) Math.round(betSize * (3 + count));

        switch (reRaiseSize) {
            case 1 :
            case 2 :
            case 3 :
            case 4 :
            case 5 :
            case 6 : return Bet.BB_6;
            case 7 : return Bet.BB_7;
            case 8 : return Bet.BB_8;
            case 9 : return Bet.BB_9;
            case 10 : return Bet.BB_10;
            case 11 :
            case 12 :
            case 13 : return Bet.BB_12;
            case 14 :
            case 15 :
            case 16 : return Bet.BB_15;
            default: return Bet.POT_100;
        }
    }

}
