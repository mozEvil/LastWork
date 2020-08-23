package ru.mozevil.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.mozevil.controller.parser.PokerParser;
import ru.mozevil.controller.robot.PokerRobot;
import ru.mozevil.controller.strategy.PokerStrategy;
import ru.mozevil.model.Environment;
import ru.mozevil.view.PokerView;

public class PokerBot implements Runnable {

    private static final Logger log = Logger.getLogger(PokerBot.class.getName());

    private PokerParser parser;
    private PokerStrategy strategy;
    private PokerRobot robot;
    private PokerView view;

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

    @Override
    public void run() {

        Environment env = null;

        try {
            // делаем снимок экрана
            parser.setImageTable(robot.grabScreen());
            System.out.print(".");

            // если настал ход hero.
            if (parser.isAction()) {

                // парсим стол
                env = parser.parseTable();

                // принимаем решение
                strategy.makeDecision(env);

                // выполняем решение
                robot.makeMove(env);

        //показываем решение
        if (view != null) {
            view.update(env);
        }
            }
        } catch (Exception e) {
            log.log(Level.ERROR, "BOT ERROR", e);
            env = null;
        }
    }
}
