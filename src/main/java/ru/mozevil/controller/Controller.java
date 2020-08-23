package ru.mozevil.controller;

import ru.mozevil.controller.parser.TableParser;
import ru.mozevil.controller.robot.RobotVM;
import ru.mozevil.controller.strategy.SnG_45_simple;
import ru.mozevil.controller.robot.vm.VMoze;
import ru.mozevil.view.PokerView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller implements PokerController {

    private final PokerView view;

    private final VMoze vm;
    private final PokerBot bot;
//    private final Grabber grabber;

    private ScheduledExecutorService executorService;
    private boolean isBotStarted;

    public Controller(PokerView view) {
        this.view = view;

        vm = new VMoze("Win7");

        bot = new PokerBot();
        bot.setRobot(new RobotVM(vm));
        bot.setParser(new TableParser());
        bot.setStrategy(new SnG_45_simple());
        bot.setView(view);

//        grabber = new Grabber(vm);
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
        executorService.scheduleWithFixedDelay(bot, 0, 1, TimeUnit.SECONDS);
//        executorService.scheduleWithFixedDelay(grabber, 0, 1, TimeUnit.SECONDS);
        isBotStarted = true;
        if (view != null) {
            view.update(null);
        }
    }

    @Override
    public void stopBot() {
        executorService.shutdown();
        vm.closeSession();
        vm.disconnect();
        isBotStarted = false;
        if (view != null) {
            view.update(null);
        }
    }
}
