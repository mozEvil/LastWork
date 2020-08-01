package ru.mozevil.view;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SpringLayout.*;
import static javax.swing.SpringLayout.NORTH;

public class InfoPanel extends JPanel {

    private JLabel streetValue;
    private JLabel comboValue;
    private JLabel decisionValue;
    private JLabel positionValue;
    private JLabel iopValue;
    private JLabel activeValue;

    public InfoPanel() {
        init();
    }

    private void init() {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(640, 120);

        JLabel street = new JLabel("Street : ");
        JLabel combo = new JLabel("Combo : ");
        JLabel decision = new JLabel("Decision : ");
        JLabel position = new JLabel("Position : ");
        JLabel iop = new JLabel("IOP : ");
        JLabel active = new JLabel("Active : ");

        streetValue = new JLabel();
        comboValue = new JLabel();
        decisionValue = new JLabel();
        positionValue = new JLabel();
        iopValue = new JLabel();
        activeValue = new JLabel();

//        setColor(colorNull);

        add(street);        add(streetValue);       add(position);      add(positionValue);
        add(combo);         add(comboValue);        add(iop);           add(iopValue);
        add(decision);      add(decisionValue);     add(active);        add(activeValue);

        // street
        layout.putConstraint(NORTH, street, 0, NORTH, this);
        layout.putConstraint(WEST, street, 0, WEST, this);
        layout.putConstraint(EAST, street, 150, WEST, street); // weight
        layout.putConstraint(SOUTH, street, 30, NORTH, street); // height
        // streetValue
        layout.putConstraint(NORTH, streetValue, 0, NORTH, this);
        layout.putConstraint(WEST, streetValue, 0, EAST, street);
        layout.putConstraint(EAST, streetValue, 200, WEST, streetValue); // weight
        layout.putConstraint(SOUTH, streetValue, 30, NORTH, streetValue); // height
        // position
        layout.putConstraint(NORTH, position, 0, NORTH, this);
        layout.putConstraint(WEST, position, 0, EAST, streetValue);
        layout.putConstraint(EAST, position, 150, WEST, position); // weight
        layout.putConstraint(SOUTH, position, 30, NORTH, position); // height
        // positionValue
        layout.putConstraint(NORTH, positionValue, 0, NORTH, this);
        layout.putConstraint(WEST, positionValue, 0, EAST, position);
        layout.putConstraint(EAST, positionValue, 100, WEST, positionValue); // weight
        layout.putConstraint(SOUTH, positionValue, 30, NORTH, positionValue); // height

        // combo
        layout.putConstraint(NORTH, combo, 0, SOUTH, street);
        layout.putConstraint(WEST, combo, 0, WEST, this);
        layout.putConstraint(EAST, combo, 150, WEST, combo); // weight
        layout.putConstraint(SOUTH, combo, 30, NORTH, combo); // height
        // comboValue
        layout.putConstraint(NORTH, comboValue, 0, SOUTH, streetValue);
        layout.putConstraint(WEST, comboValue, 0, EAST, combo);
        layout.putConstraint(EAST, comboValue, 200, WEST, comboValue); // weight
        layout.putConstraint(SOUTH, comboValue, 30, NORTH, comboValue); // height
        // iop
        layout.putConstraint(NORTH, iop, 0, SOUTH, position);
        layout.putConstraint(WEST, iop, 0, EAST, comboValue);
        layout.putConstraint(EAST, iop, 150, WEST, iop); // weight
        layout.putConstraint(SOUTH, iop, 30, NORTH, iop); // height
        // iopValue
        layout.putConstraint(NORTH, iopValue, 0, SOUTH, positionValue);
        layout.putConstraint(WEST, iopValue, 0, EAST, iop);
        layout.putConstraint(EAST, iopValue, 100, WEST, iopValue); // weight
        layout.putConstraint(SOUTH, iopValue, 30, NORTH, iopValue); // height

        // decision
        layout.putConstraint(NORTH, decision, 0, SOUTH, combo);
        layout.putConstraint(WEST, decision, 0, WEST, this);
        layout.putConstraint(EAST, decision, 150, WEST, decision); // weight
        layout.putConstraint(SOUTH, decision, 30, NORTH, decision); // height
        // decisionValue
        layout.putConstraint(NORTH, decisionValue, 0, SOUTH, comboValue);
        layout.putConstraint(WEST, decisionValue, 0, EAST, decision);
        layout.putConstraint(EAST, decisionValue, 200, WEST, decisionValue); // weight
        layout.putConstraint(SOUTH, decisionValue, 30, NORTH, decisionValue); // height
        // active
        layout.putConstraint(NORTH, active, 0, SOUTH, iop);
        layout.putConstraint(WEST, active, 0, EAST, decisionValue);
        layout.putConstraint(EAST, active, 150, WEST, active); // weight
        layout.putConstraint(SOUTH, active, 30, NORTH, active); // height
        // activeValue
        layout.putConstraint(NORTH, activeValue, 0, SOUTH, iopValue);
        layout.putConstraint(WEST, activeValue, 0, EAST, active);
        layout.putConstraint(EAST, activeValue, 100, WEST, activeValue); // weight
        layout.putConstraint(SOUTH, activeValue, 30, NORTH, activeValue); // height

        Dimension size = new Dimension(150, 30);

        Font plainFont = new Font("Verdana", Font.PLAIN, 12);
        Font boldFont = new Font("Verdana", Font.BOLD, 12);

//        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        street.setSize(size);
        street.setMaximumSize(size);
        street.setHorizontalAlignment(JLabel.CENTER);
        street.setFont(boldFont);
        street.setOpaque(true);

        streetValue.setSize(size);
        streetValue.setMaximumSize(size);
        streetValue.setHorizontalAlignment(JLabel.CENTER);
        streetValue.setFont(plainFont);
        streetValue.setOpaque(true);

        combo.setSize(size);
        combo.setMaximumSize(size);
        combo.setHorizontalAlignment(JLabel.CENTER);
        combo.setFont(boldFont);
        combo.setOpaque(true);

        comboValue.setSize(size);
        comboValue.setMaximumSize(size);
        comboValue.setHorizontalAlignment(JLabel.CENTER);
        comboValue.setFont(plainFont);
        comboValue.setOpaque(true);

        decision.setSize(size);
        decision.setMaximumSize(size);
        decision.setHorizontalAlignment(JLabel.CENTER);
        decision.setFont(boldFont);
        decision.setOpaque(true);

        decisionValue.setSize(size);
        decisionValue.setMaximumSize(size);
        decisionValue.setHorizontalAlignment(JLabel.CENTER);
        decisionValue.setFont(plainFont);
        decisionValue.setOpaque(true);

        position.setSize(size);
        position.setMaximumSize(size);
        position.setHorizontalAlignment(JLabel.CENTER);
        position.setFont(boldFont);
        position.setOpaque(true);

        positionValue.setSize(size);
        positionValue.setMaximumSize(size);
        positionValue.setHorizontalAlignment(JLabel.CENTER);
        positionValue.setFont(plainFont);
        positionValue.setOpaque(true);

        iop.setSize(size);
        iop.setMaximumSize(size);
        iop.setHorizontalAlignment(JLabel.CENTER);
        iop.setFont(boldFont);
        iop.setOpaque(true);

        iopValue.setSize(size);
        iopValue.setMaximumSize(size);
        iopValue.setHorizontalAlignment(JLabel.CENTER);
        iopValue.setFont(plainFont);
        iopValue.setOpaque(true);

        active.setSize(size);
        active.setMaximumSize(size);
        active.setHorizontalAlignment(JLabel.CENTER);
        active.setFont(boldFont);
        active.setOpaque(true);

        activeValue.setSize(size);
        activeValue.setMaximumSize(size);
        activeValue.setHorizontalAlignment(JLabel.CENTER);
        activeValue.setFont(plainFont);
        activeValue.setOpaque(true);
    }

    public void setStreetValue(String value) {
        streetValue.setText(value);
    }

    public void setComboValue(String value) {
        comboValue.setText(value);
    }

    public void setDecisionValue(String value) {
        decisionValue.setText(value);
    }

    public void setPositionValue(String value) {
        positionValue.setText(value);
    }

    public void setIopValue(String value) {
        iopValue.setText(value);
    }

    public void setActivePlayersValue(String value) {
        activeValue.setText(value);
    }
}
