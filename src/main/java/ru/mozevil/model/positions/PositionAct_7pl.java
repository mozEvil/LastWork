package ru.mozevil.model.positions;

public enum PositionAct_7pl implements ActionNumber {
    ACT_1(Position.SB),
    ACT_2(Position.BB),
    ACT_3(Position.MP),
    ACT_4(Position.MP),
    ACT_5(Position.HJ),
    ACT_6(Position.CO),
    ACT_7(Position.BTN);

    private Position position;

    PositionAct_7pl(Position position) {
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
