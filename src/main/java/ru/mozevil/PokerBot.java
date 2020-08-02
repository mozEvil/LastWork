package ru.mozevil;

import ru.mozevil.controller.parser.PokerParser;
import ru.mozevil.controller.robot.PokerRobot;
import ru.mozevil.controller.strategy.PokerStrategy;
import ru.mozevil.model.Decision;
import ru.mozevil.model.Environment;
import ru.mozevil.model.Table;
import ru.mozevil.view.PokerView;

public class PokerBot implements Runnable {

    private PokerParser parser;
    private PokerStrategy strategy;
    private PokerRobot robot;
    private PokerView view;

    private Decision decision;

    private boolean autoMove;

    public void setParser(PokerParser parser) {
        this.parser = parser;
    }

    public void setRobot(PokerRobot robot) {
        this.robot = robot;
    }

    public void setStrategy(PokerStrategy strategy) {
        this.strategy = strategy;
    }

    public void setView(PokerView view) {
        this.view = view;
    }

    public void setAutoMove(boolean autoMove) {
        this.autoMove = autoMove;
    }

    public Decision getDecision() {
        return decision;
    }

    public void makeMove(){
        if (decision != null) robot.makeMove(decision);
    }

    @Override
    public void run() {
        // делаем снимок экрана
        parser.setImageTable(robot.grabScreen());
        System.out.print(".");

        // если настал ход hero.
        if (parser.isAction()) {
            // парсим стол
            Table table = parser.parseTable();

            // заворачиваем стол в обертку
            Environment env = new Environment(table);

            // принимаем решение, в соответсвии со стратегией
            decision = strategy.makeDecision(env);

            // показываем решение
            view.setEnvironment(env);
            view.setDecision(decision);
            view.update();

            // выполняем принятое решение, если нужно
            if (autoMove) makeMove();

        } else { // если ход hero не наступил, то ничего не делаем
            view.setEnvironment(null);
            view.setDecision(null);
            decision = null;
            view.update();
        }
    }
}
