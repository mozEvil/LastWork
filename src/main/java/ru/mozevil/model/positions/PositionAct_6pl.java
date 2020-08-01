package ru.mozevil.model.positions;

public enum PositionAct_6pl implements ActionNumber {
    ACT_1(Position.SB),
    ACT_2(Position.BB),
    ACT_3(Position.MP),
    ACT_4(Position.HJ),
    ACT_5(Position.CO),
    ACT_6(Position.BTN);

    private Position position;

    PositionAct_6pl(Position position) {
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
