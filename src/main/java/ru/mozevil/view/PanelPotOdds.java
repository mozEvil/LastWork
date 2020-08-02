package ru.mozevil.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class PanelPotOdds extends JPanel {

    private JLabel potOddsValue;

    public PanelPotOdds() {
        init();
    }

    private void init() {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(170, 50);

        JLabel label = new JLabel("Pot odds");

        potOddsValue = new JLabel();

        add(label);

        layout.putConstraint(NORTH, label, 0, NORTH, this);
        layout.putConstraint(WEST, label, 0, WEST, this);
        layout.putConstraint(EAST, label, 170, WEST, label); // weight
        layout.putConstraint(SOUTH, label, 25, NORTH, label); // height

        add(potOddsValue);

        layout.putConstraint(NORTH, potOddsValue, 0, SOUTH, label);
        layout.putConstraint(WEST, potOddsValue, 0, WEST, this);
        layout.putConstraint(EAST, potOddsValue, 170, WEST, potOddsValue); // weight
        layout.putConstraint(SOUTH, potOddsValue, 25, NORTH, potOddsValue); // height


        Dimension size = new Dimension(170, 25);

        Font plainFont = new Font("Verdana", Font.PLAIN, 12);
        Font boldFont = new Font("Verdana", Font.BOLD, 12);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        label.setSize(size);
        label.setMaximumSize(size);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(boldFont);
        label.setOpaque(true);

        potOddsValue.setBorder(solidBorder);
        potOddsValue.setSize(size);
        potOddsValue.setMaximumSize(size);
        potOddsValue.setHorizontalAlignment(JLabel.CENTER);
        potOddsValue.setFont(plainFont);
        potOddsValue.setOpaque(true);


    }

    public void setPotOddsValue(String value) {
        potOddsValue.setText(value);
    }

}
