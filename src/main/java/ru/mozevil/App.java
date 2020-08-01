package ru.mozevil;

import ru.mozevil.controller.parser.TableParser;
import ru.mozevil.controller.robot.RobotKey;
import ru.mozevil.controller.strategy.SnG_45_simple;
import ru.mozevil.view.TableView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main( String[] args ) {

        PokerBot pokerBot = new PokerBot();
        pokerBot.setParser(new TableParser());
        pokerBot.setStrategy(new SnG_45_simple());
        pokerBot.setRobot(new RobotKey());
        pokerBot.setView(new TableView());
        pokerBot.setAutoMove(false);

        // парсим каждую секунду
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(pokerBot, 0, 1, TimeUnit.SECONDS);

        System.out.println("start");
    }

}