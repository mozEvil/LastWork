package ru.mozevil.controller.strategy;

import ru.mozevil.model.DF;
import ru.mozevil.model.Decision;
import ru.mozevil.model.Environment;

public class AlwaysFold implements PokerStrategy {

    @Override
    public void makeDecision(Environment env) {

        env.setDecision(DF.fold());
    }
}
