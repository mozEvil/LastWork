package ru.mozevil.model.positions;

public enum PositionAct_9pl implements ActionNumber {
    ACT_1(Position.SB),
    ACT_2(Position.BB),
    ACT_3(Position.EP),
    ACT_4(Position.EP),
    ACT_5(Position.MP),
    ACT_6(Position.MP),
    ACT_7(Position.HJ),
    ACT_8(Position.CO),
    ACT_9(Position.BTN);

    private Position position;

    PositionAct_9pl(Position position) {
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
