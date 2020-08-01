package ru.mozevil.model.positions;

public enum PositionAct_2pl implements ActionNumber {
    ACT_1(Position.BB),
    ACT_2(Position.SB);//BTN

    private Position position;

    PositionAct_2pl(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public ActionNumber nextAct() {
        int i = ordinal() + 1;
        if (i == values().length) i = 0;
        return values()[i];
    }

}
