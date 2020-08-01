package ru.mozevil.model.positions;

public interface ActionNumber {
    Position getPosition();
    ActionNumber nextAct();
    int ordinal();
}
