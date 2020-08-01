package ru.mozevil.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class PanelCombo extends JPanel {

    private JLabel comboValue;

    public PanelCombo() {
        init();
    }

    private void init() {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(170, 50);

        JLabel combo = new JLabel("Combination");

        comboValue = new JLabel();

        add(combo);

        layout.putConstraint(NORTH, combo, 0, NORTH, this);
        layout.putConstraint(WEST, combo, 0, WEST, this);
        layout.putConstraint(EAST, combo, 170, WEST, combo); // weight
        layout.putConstraint(SOUTH, combo, 25, NORTH, combo); // height

        add(comboValue);

        layout.putConstraint(NORTH, comboValue, 0, SOUTH, combo);
        layout.putConstraint(WEST, comboValue, 0, WEST, this);
        layout.putConstraint(EAST, comboValue, 170, WEST, comboValue); // weight
        layout.putConstraint(SOUTH, comboValue, 25, NORTH, comboValue); // height


        Dimension size = new Dimension(170, 25);

        Font plainFont = new Font("Verdana", Font.PLAIN, 12);
        Font boldFont = new Font("Verdana", Font.BOLD, 12);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        combo.setSize(size);
        combo.setMaximumSize(size);
        combo.setHorizontalAlignment(JLabel.CENTER);
        combo.setFont(boldFont);
        combo.setOpaque(true);

        comboValue.setBorder(solidBorder);
        comboValue.setSize(size);
        comboValue.setMaximumSize(size);
        comboValue.setHorizontalAlignment(JLabel.CENTER);
        comboValue.setFont(plainFont);
        comboValue.setOpaque(true);


    }

    public void setComboValue(String value) {
        comboValue.setText(value);
    }

}
