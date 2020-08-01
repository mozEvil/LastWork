package ru.mozevil.view;

import ru.mozevil.model.Decision;
import ru.mozevil.model.Environment;

public interface PokerView {

    void setEnvironment(Environment env);

    void setDecision(Decision decision);

    void update();
}
