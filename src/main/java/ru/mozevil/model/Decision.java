package ru.mozevil.model;

public class Decision {
    private Move move;
    private Bet value;

    public Decision() {
        this(Move.FOLD);
    }

    public Decision(Move move) {
        this.move = move;
        this.value = Bet.NOTHING;
    }

    public Decision(Move move, Bet value) {
        this.move = move;
        this.value = value;
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
        String str1 = "" + move;
        String str2 = " (" + value + ")";
        if (value == Bet.NOTHING) return str1;
        return str1 + str2;
    }
}
