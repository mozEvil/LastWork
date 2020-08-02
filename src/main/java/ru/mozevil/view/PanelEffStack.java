package ru.mozevil.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class PanelEffStack extends JPanel {

    private JLabel effStackValue;

    public PanelEffStack() {
        init();
    }

    private void init() {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(170, 50);

        JLabel label = new JLabel("Eff stack");

        effStackValue = new JLabel();

        add(label);

        layout.putConstraint(NORTH, label, 0, NORTH, this);
        layout.putConstraint(WEST, label, 0, WEST, this);
        layout.putConstraint(EAST, label, 170, WEST, label); // weight
        layout.putConstraint(SOUTH, label, 25, NORTH, label); // height

        add(effStackValue);

        layout.putConstraint(NORTH, effStackValue, 0, SOUTH, label);
        layout.putConstraint(WEST, effStackValue, 0, WEST, this);
        layout.putConstraint(EAST, effStackValue, 170, WEST, effStackValue); // weight
        layout.putConstraint(SOUTH, effStackValue, 25, NORTH, effStackValue); // height


        Dimension size = new Dimension(170, 25);

        Font plainFont = new Font("Verdana", Font.PLAIN, 12);
        Font boldFont = new Font("Verdana", Font.BOLD, 12);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        label.setSize(size);
        label.setMaximumSize(size);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(boldFont);
        label.setOpaque(true);

        effStackValue.setBorder(solidBorder);
        effStackValue.setSize(size);
        effStackValue.setMaximumSize(size);
        effStackValue.setHorizontalAlignment(JLabel.CENTER);
        effStackValue.setFont(plainFont);
        effStackValue.setOpaque(true);


    }

    public void setEffStackValue(String value) {
        effStackValue.setText(value);
    }

}
