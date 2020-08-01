package ru.mozevil.controller.strategy;

import ru.mozevil.model.Decision;
import ru.mozevil.model.Environment;

public interface PokerStrategy {
    Decision makeDecision(Environment env);
}
