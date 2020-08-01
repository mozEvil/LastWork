package ru.mozevil.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class PanelDecision extends JPanel {

    private JLabel decisionValue;

    public PanelDecision() {
        init();
    }

    private void init() {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(170, 50);

        JLabel decision = new JLabel("Decision");

        decisionValue = new JLabel();

        add(decision);

        layout.putConstraint(NORTH, decision, 0, NORTH, this);
        layout.putConstraint(WEST, decision, 0, WEST, this);
        layout.putConstraint(EAST, decision, 170, WEST, decision); // weight
        layout.putConstraint(SOUTH, decision, 25, NORTH, decision); // height

        add(decisionValue);

        layout.putConstraint(NORTH, decisionValue, 0, SOUTH, decision);
        layout.putConstraint(WEST, decisionValue, 0, WEST, this);
        layout.putConstraint(EAST, decisionValue, 170, WEST, decisionValue); // weight
        layout.putConstraint(SOUTH, decisionValue, 25, NORTH, decisionValue); // height


        Dimension size = new Dimension(170, 25);

        Font plainFont = new Font("Verdana", Font.PLAIN, 12);
        Font boldFont = new Font("Verdana", Font.BOLD, 12);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        decision.setSize(size);
        decision.setMaximumSize(size);
        decision.setHorizontalAlignment(JLabel.CENTER);
        decision.setFont(boldFont);
        decision.setOpaque(true);

        decisionValue.setBorder(solidBorder);
        decisionValue.setSize(size);
        decisionValue.setMaximumSize(size);
        decisionValue.setHorizontalAlignment(JLabel.CENTER);
        decisionValue.setFont(plainFont);
        decisionValue.setOpaque(true);


    }

    public void setDecisionValue(String value) {
        decisionValue.setText(value);
    }

}
