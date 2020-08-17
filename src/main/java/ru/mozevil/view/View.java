package ru.mozevil.view;

import ru.mozevil.controller.Controller;
import ru.mozevil.controller.PokerController;
import ru.mozevil.model.Decision;
import ru.mozevil.model.Environment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.SpringLayout.*;

public class View  extends JFrame implements PokerView {

    private Environment env;
    private Decision decision;

    private final ControlPanel controlPanel;
    private final InfoPanel200 panelDecision;
    private final InfoPanel200 panelCombo;
    private final InfoPanel200 panelBoard;
    private final InfoPanel100 panelHand;
    private final InfoPanel100 panelPos;
    private final InfoPanel100 panelRealStack;

    public View() throws HeadlessException {
        super("Poker Auto Player");

        PokerController controller = new Controller(this);
        controlPanel = new ControlPanel(controller);

        panelHand = new InfoPanel100("Hand");
        panelBoard = new InfoPanel200("Board");
        panelCombo = new InfoPanel200("Combo");
        panelDecision = new InfoPanel200("Decision");
        panelPos = new InfoPanel100("Position");
        panelRealStack = new InfoPanel100("Real Stack");

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(715,165);
        setLocation(350, 250);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                if (controller.isBotStarted()) controller.stopBot();
                dispose();
                System.exit(0);
            }
        });

        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        addPanel(layout, controlPanel, 0, 0);
        addPanel(layout, panelHand, 0, 200);
        addPanel(layout, panelBoard, 0, 300);
        addPanel(layout, panelCombo, 0, 500);
//------2'nd line
        addPanel(layout, panelDecision, 60, 0);
        addPanel(layout, panelPos, 60, 200);
        addPanel(layout, panelRealStack, 60, 300);

        setVisible(true);
    }

    private void addPanel(SpringLayout layout, JPanel panel, int top, int left) {
        add(panel);
        layout.putConstraint(NORTH, panel, top, NORTH, getContentPane());   // top
        layout.putConstraint(WEST, panel, left, WEST , getContentPane());   // left
        layout.putConstraint(EAST, panel, panel.getWidth(), WEST , panel);   // weight
        layout.putConstraint(SOUTH, panel, panel.getHeight(), NORTH , panel); // height
    }

    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
    }

    @Override
    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    @Override
    public void update() {
        controlPanel.update();
        panelDecision.update(decision == null ? "" : decision.toString());
        panelHand.update(env == null ? "" : env.getHeroHand().toString());
        panelBoard.update(env == null ? "" : env.getTableCards().toString());
        panelCombo.update(env == null ? "" : "" + env.getBestCombo());
        panelPos.update(env == null ? "" : env.getHeroPosition().toString());
        panelRealStack.update(env == null ? "" : "" + env.getRealHeroStackSize());

    }
}
