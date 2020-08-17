package ru.mozevil.controller;

import ru.mozevil.controller.parser.TableParser;
import ru.mozevil.controller.robot.RobotKey;
import ru.mozevil.controller.robot.RobotVM;
import ru.mozevil.controller.strategy.SnG_45_simple;
import ru.mozevil.controller.vm.VMoze;
import ru.mozevil.view.PokerView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller implements PokerController {

    private final PokerView view;

    private final VMoze vm;
    private final PokerBot bot;

    private ScheduledExecutorService executorService;
    private boolean isBotStarted;

    public Controller(PokerView view) {
        this.view = view;
        vm = new VMoze("Win7");
        bot = new PokerBot();
//        bot.setRobot(new RobotVM(vm));
        bot.setRobot(new RobotKey());
        bot.setStrategy(new SnG_45_simple());
        bot.setParser(new TableParser());
        bot.setView(view);
        bot.setAutoMove(false);
    }

    @Override
    public boolean isBotStarted() {
        return isBotStarted;
    }

    @Override
    public void startBot() {
        vm.connect();
        vm.openSession();
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(bot, 0, 1, TimeUnit.SECONDS);
        isBotStarted = true;
        view.update(null);
    }

    @Override
    public void stopBot() {
        executorService.shutdown();
        vm.closeSession();
        vm.disconnect();
        isBotStarted = false;
        view.update(null);
    }
}
