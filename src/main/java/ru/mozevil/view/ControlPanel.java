package ru.mozevil.view;

import ru.mozevil.controller.PokerController;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class ControlPanel extends JPanel {

    private final PokerController controller;

    private JButton btnStart;
    private JButton btnStop;

    public ControlPanel(PokerController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(200, 50);

        JLabel label = new JLabel("Play poker");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 12));
        label.setOpaque(true);

        add(label);
        layout.putConstraint(NORTH, label, 0, NORTH, this);
        layout.putConstraint(WEST, label, 10, WEST, this);
        layout.putConstraint(EAST, label, 180, WEST, label); // weight
        layout.putConstraint(SOUTH, label, 25, NORTH, label); // height

        btnStart = new JButton("Start");
        btnStart.addActionListener(e -> handleStart());
        btnStart.setEnabled(true);

        add(btnStart);

        layout.putConstraint(NORTH, btnStart, 25, NORTH, this);
        layout.putConstraint(WEST, btnStart, 10, WEST, this);
        layout.putConstraint(EAST, btnStart, 85, WEST, btnStart); // weight
        layout.putConstraint(SOUTH, btnStart, 25, NORTH, btnStart); // height

        btnStop = new JButton("Stop");
        btnStop.addActionListener(e -> handleStop());
        btnStop.setEnabled(false);

        add(btnStop);

        layout.putConstraint(NORTH, btnStop, 25, NORTH, this);
        layout.putConstraint(WEST, btnStop, 105, WEST, this);
        layout.putConstraint(EAST, btnStop, 85, WEST, btnStop); // weight
        layout.putConstraint(SOUTH, btnStop, 25, NORTH, btnStop); // height

    }

    public void handleStart() {
        controller.startBot();
    }

    public void handleStop() {
        controller.stopBot();
    }

    public void update() {
        btnStart.setEnabled(!controller.isBotStarted());
        btnStop.setEnabled(controller.isBotStarted());
    }

}
