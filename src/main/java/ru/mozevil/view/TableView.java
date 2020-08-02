package ru.mozevil.view;

import ru.mozevil.model.*;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class TableView extends JFrame implements PokerView {

    private Environment env;
    private Decision decision;

    PanelBotSettings panelBot;

    private PanelDecision panelDecision;
    private PanelCombo panelCombo;
    private PanelPosition panelPosition;
    private PanelRealStack panelRealStack;
    private PanelEffStack panelEffStack;
    private PanelPotOdds panelPotOdds;

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
        panelRealStack = new PanelRealStack();
        panelEffStack = new PanelEffStack();
        panelPotOdds = new PanelPotOdds();

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

        add(panelRealStack);

        layout.putConstraint(NORTH, panelRealStack,150, NORTH, contentPane);
        layout.putConstraint(WEST, panelRealStack,0, WEST , contentPane);
        layout.putConstraint(EAST, panelRealStack,170, WEST , panelRealStack);        // weight
        layout.putConstraint(SOUTH, panelRealStack,50, NORTH , panelRealStack);      // height

        add(panelEffStack);

        layout.putConstraint(NORTH, panelEffStack,200, NORTH, contentPane);
        layout.putConstraint(WEST, panelEffStack,0, WEST , contentPane);
        layout.putConstraint(EAST, panelEffStack,170, WEST , panelEffStack);        // weight
        layout.putConstraint(SOUTH, panelEffStack,50, NORTH , panelEffStack);      // height

        add(panelPotOdds);

        layout.putConstraint(NORTH, panelPotOdds,250, NORTH, contentPane);
        layout.putConstraint(WEST, panelPotOdds,0, WEST , contentPane);
        layout.putConstraint(EAST, panelPotOdds,170, WEST , panelPotOdds);        // weight
        layout.putConstraint(SOUTH, panelPotOdds,50, NORTH , panelPotOdds);      // height

        //=================================

        add(panelBot);

        layout.putConstraint(NORTH, panelBot,550, NORTH, contentPane);
        layout.putConstraint(WEST, panelBot,0, WEST , contentPane);
        layout.putConstraint(EAST, panelBot,170, WEST , panelBot);        // weight
        layout.putConstraint(SOUTH, panelBot,100, NORTH , panelBot);      // height

        setLocation(1196, 0);

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
            panelPosition.setPositionValue(env.getHeroPosition().toString());
            panelRealStack.setRealStackValue(env.getRealHeroStackSize() + "");
            panelEffStack.setEffStackValue("" + env.getRealEffectiveStackSize());
            panelPotOdds.setPotOddsValue("" + env.getPotOdds());

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
        panelRealStack.setRealStackValue("");
        panelEffStack.setEffStackValue("");
        panelPotOdds.setPotOddsValue("");
    }
}
