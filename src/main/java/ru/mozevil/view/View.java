package ru.mozevil.view;

import ru.mozevil.controller.Controller;
import ru.mozevil.controller.PokerController;
import ru.mozevil.model.Environment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class View  extends JFrame implements PokerView {

    private final GamePanel gamePanel;

    public View() throws HeadlessException {
        super("Poker Auto Player");

        PokerController controller = new Controller(this);
        gamePanel = new GamePanel(controller);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        setSize(700,550);
        setLocationRelativeTo(null);
//        setLocation(350, 50);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                if (controller.isBotStarted()) controller.stopBot();
                dispose();
                System.exit(0);
            }
        });

        add(gamePanel);
        pack();
        setVisible(true);
    }

    @Override
    public void update(Environment env) {
        gamePanel.update(env);
    }
}
