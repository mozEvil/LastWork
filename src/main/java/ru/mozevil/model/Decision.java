package ru.mozevil.model;

import org.apache.log4j.Logger;

import java.util.Arrays;

public class Decision {

    private static final Logger log = Logger.getLogger(Decision.class);

    private Move move;
    private Bet value;

    public Decision() {
        this(Move.FOLD, Bet.NULL);
    }

    public Decision(Move move) {
        this(move, Bet.NULL);
    }

    public Decision(Move move, Bet value) {
        this.move = move;
        this.value = value;
//        log.debug(move + "-" + value +" : " + Arrays.toString(Thread.currentThread().getStackTrace()));
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Bet getValue() {
        return value;
    }

    public void setValue(Bet value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value == Bet.NULL ? move + "" : move + " (" + value + ")";
    }
}
