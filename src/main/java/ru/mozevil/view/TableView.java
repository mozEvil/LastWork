package ru.mozevil.view;

import ru.mozevil.model.*;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class TableView extends JFrame implements PokerView {

    private Environment env;
    private Decision decision;

    private PanelBotSettings panelBot;

    private PanelDecision panelDecision;
    private PanelCombo panelCombo;
    private PanelPosition panelPosition;
    private PanelIOP panelIOP;
    private PanelActivePlayers panelActivePlayers;
    private PanelStreet panelStreet;

    public TableView() throws HeadlessException {
        super("My music");
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(170,700);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        Container contentPane = getContentPane(); // main container

        panelBot = new PanelBotSettings(this);

        panelDecision = new PanelDecision();
        panelCombo = new PanelCombo();
        panelPosition = new PanelPosition();
        panelIOP = new PanelIOP();
        panelActivePlayers = new PanelActivePlayers();
        panelStreet = new PanelStreet();

        add(panelDecision);

        layout.putConstraint(NORTH, panelDecision,0, NORTH, contentPane);
        layout.putConstraint(WEST, panelDecision,0, WEST , contentPane);
        layout.putConstraint(EAST, panelDecision,170, WEST , panelDecision);        // weight
        layout.putConstraint(SOUTH, panelDecision,50, NORTH , panelDecision);      // height

        add(panelCombo);

        layout.putConstraint(NORTH, panelCombo,50, NORTH, contentPane);
        layout.putConstraint(WEST, panelCombo,0, WEST , contentPane);
        layout.putConstraint(EAST, panelCombo,170, WEST , panelCombo);        // weight
        layout.putConstraint(SOUTH, panelCombo,50, NORTH , panelCombo);      // height

        add(panelPosition);

        layout.putConstraint(NORTH, panelPosition,100, NORTH, contentPane);
        layout.putConstraint(WEST, panelPosition,0, WEST , contentPane);
        layout.putConstraint(EAST, panelPosition,170, WEST , panelPosition);        // weight
        layout.putConstraint(SOUTH, panelPosition,50, NORTH , panelPosition);      // height

        add(panelIOP);

        layout.putConstraint(NORTH, panelIOP,150, NORTH, contentPane);
        layout.putConstraint(WEST, panelIOP,0, WEST , contentPane);
        layout.putConstraint(EAST, panelIOP,170, WEST , panelIOP);        // weight
        layout.putConstraint(SOUTH, panelIOP,50, NORTH , panelIOP);      // height

        add(panelActivePlayers);

        layout.putConstraint(NORTH, panelActivePlayers,200, NORTH, contentPane);
        layout.putConstraint(WEST, panelActivePlayers,0, WEST , contentPane);
        layout.putConstraint(EAST, panelActivePlayers,170, WEST , panelActivePlayers);        // weight
        layout.putConstraint(SOUTH, panelActivePlayers,50, NORTH , panelActivePlayers);      // height

        add(panelStreet);

        layout.putConstraint(NORTH, panelStreet,250, NORTH, contentPane);
        layout.putConstraint(WEST, panelStreet,0, WEST , contentPane);
        layout.putConstraint(EAST, panelStreet,170, WEST , panelStreet);        // weight
        layout.putConstraint(SOUTH, panelStreet,50, NORTH , panelStreet);      // height

        add(panelBot);

        layout.putConstraint(NORTH, panelBot,550, NORTH, contentPane);
        layout.putConstraint(WEST, panelBot,0, WEST , contentPane);
        layout.putConstraint(EAST, panelBot,170, WEST , panelBot);        // weight
        layout.putConstraint(SOUTH, panelBot,50, NORTH , panelBot);      // height

        setLocation(1198, 0);

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
            panelStreet.setStreetValue(env.getStreet().toString());
            panelPosition.setPositionValue(env.getHeroPosition().toString());
            panelIOP.setIopValue(env.getHeroIOP().toString());
            panelActivePlayers.setActivePlayersValue("" + env.getActivePlayersCount());

            if (env.getStreet() == Street.PREFLOP) {
                panelCombo.setComboValue(env.getHeroHand().toString());
            } else {
                panelCombo.setComboValue(env.getBestCombo().toString());
            }
        }
        if (decision != null) {
            panelDecision.setDecisionValue(decision.toString());
        }
    }

    private void clear() {
        panelDecision.setDecisionValue("");
        panelCombo.setComboValue("");
        panelPosition.setPositionValue("");
        panelStreet.setStreetValue("");
        panelIOP.setIopValue("");
        panelActivePlayers.setActivePlayersValue("");
    }
}
