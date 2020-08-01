package ru.mozevil.view;

import ru.mozevil.model.*;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class TableViewAurora extends JFrame implements PokerView {

    private Environment env;
    private Decision decision;

    private InfoPanel panel;

    public TableViewAurora() throws HeadlessException {
        super("Statistics by mozEvil");
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640,140);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        Container contentPane = getContentPane(); // main container

        panel = new InfoPanel();
        add(panel);

        layout.putConstraint(NORTH, panel,0, NORTH, contentPane);
        layout.putConstraint(WEST, panel,0, WEST , contentPane);
        layout.putConstraint(EAST, panel,640, WEST , panel);        // weight
        layout.putConstraint(SOUTH, panel,120, NORTH , panel);      // height

        setLocation(0, 600);

        setResizable(false);
        setVisible(true);
        setAlwaysOnTop(true);
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
        clear();
        if (env != null) {
            panel.setStreetValue(env.getStreet().toString());
            panel.setPositionValue(env.getHeroPosition().toString());
            panel.setIopValue(env.getHeroIOP().toString());
            panel.setActivePlayersValue("" + env.getActivePlayersCount());

            if (env.getStreet() == Street.PREFLOP) {
                panel.setComboValue(env.getHeroHand().toString());
            } else {
                panel.setComboValue(env.getBestCombo().toString());
            }
        }
        if (decision != null) {
            panel.setDecisionValue(decision.toString());
        }
    }

    private void clear() {
        panel.setStreetValue("");
        panel.setComboValue("");
        panel.setDecisionValue("");
        panel.setPositionValue("");
        panel.setIopValue("");
        panel.setActivePlayersValue("");
    }
}
