package ru.mozevil.controller.strategy;

import ru.mozevil.model.Decision;
import ru.mozevil.model.Environment;

public class AlwaysFold implements PokerStrategy {

    @Override
    public Decision makeDecision(Environment env) {

        return new Decision();
    }
}
