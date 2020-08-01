package ru.mozevil.view;

import ru.mozevil.PokerBot;
import ru.mozevil.controller.parser.TableParser;
import ru.mozevil.controller.robot.RobotKey;
import ru.mozevil.controller.strategy.SnG_45_simple;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static javax.swing.SpringLayout.*;

public class PanelBotSettings extends JPanel {

    private final PokerBot pokerBot;
    private ScheduledExecutorService executorService;

    private boolean isStarted;

    private JButton btnStart;
    private JButton btnStop;

    public PanelBotSettings(PokerView view) {
        init();
        pokerBot = new PokerBot();
        pokerBot.setView(view);
    }

    private void init() {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(170, 50);

        JLabel settings = new JLabel("Play music");

        add(settings);

        layout.putConstraint(NORTH, settings, 0, NORTH, this);
        layout.putConstraint(WEST, settings, 0, WEST, this);
        layout.putConstraint(EAST, settings, 170, WEST, settings); // weight
        layout.putConstraint(SOUTH, settings, 25, NORTH, settings); // height

        Dimension size = new Dimension(170, 25);

        Font plainFont = new Font("Verdana", Font.PLAIN, 12);
        Font boldFont = new Font("Verdana", Font.BOLD, 12);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        settings.setSize(size);
        settings.setMaximumSize(size);
        settings.setHorizontalAlignment(JLabel.CENTER);
        settings.setFont(boldFont);
        settings.setOpaque(true);

        btnStart = new JButton("Start");
        btnStart.addActionListener(e -> handleStart());

        btnStop = new JButton("Stop");
        btnStop.addActionListener(e -> handleStop());

        add(btnStart);

        layout.putConstraint(NORTH, btnStart, 25, NORTH, this);
        layout.putConstraint(WEST, btnStart, 0, WEST, this);
        layout.putConstraint(EAST, btnStart, 85, WEST, btnStart); // weight
        layout.putConstraint(SOUTH, btnStart, 25, NORTH, btnStart); // height

        add(btnStop);

        layout.putConstraint(NORTH, btnStop, 25, NORTH, this);
        layout.putConstraint(WEST, btnStop, 85, WEST, this);
        layout.putConstraint(EAST, btnStop, 85, WEST, btnStop); // weight
        layout.putConstraint(SOUTH, btnStop, 25, NORTH, btnStop); // height

        btnStart.setEnabled(!isStarted);
        btnStop.setEnabled(isStarted);
    }

    public void handleStart() {
        // настройки бота
        pokerBot.setParser(new TableParser());
        pokerBot.setStrategy(new SnG_45_simple());
        pokerBot.setRobot(new RobotKey());
        pokerBot.setAutoMove(false);

        // парсим каждую секунду
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(pokerBot, 0, 1, TimeUnit.SECONDS);
        System.out.println();
        System.out.println("start");

        isStarted = true;
        btnStart.setEnabled(!isStarted);
        btnStop.setEnabled(isStarted);
    }

    public void handleStop() {
        executorService.shutdown();
        System.out.println();
        System.out.println("stop");

        isStarted = false;
        btnStart.setEnabled(!isStarted);
        btnStop.setEnabled(isStarted);
    }

}
