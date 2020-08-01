package ru.mozevil.controller.grabber;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Grabber {

    public static void main(String[] args) {

        // сейвим каждую секунду
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new ScreenGrabber(), 0, 1, TimeUnit.SECONDS);
    }
}
