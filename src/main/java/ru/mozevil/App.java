package ru.mozevil;

import ru.mozevil.controller.parser.TableParser;
import ru.mozevil.controller.robot.RobotKey;
import ru.mozevil.controller.strategy.SnG_45_simple;
import ru.mozevil.view.PokerView;
import ru.mozevil.view.TableView;
import ru.mozevil.view.TableViewAurora;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main( String[] args ) {

        new TableView();


    }

}