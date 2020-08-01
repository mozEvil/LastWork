package ru.mozevil.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class PanelRealStack extends JPanel {

    private JLabel realStackValue;

    public PanelRealStack() {
        init();
    }

    private void init() {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(170, 50);

        JLabel label = new JLabel("Real stack size");

        realStackValue = new JLabel();

        add(label);

        layout.putConstraint(NORTH, label, 0, NORTH, this);
        layout.putConstraint(WEST, label, 0, WEST, this);
        layout.putConstraint(EAST, label, 170, WEST, label); // weight
        layout.putConstraint(SOUTH, label, 25, NORTH, label); // height

        add(realStackValue);

        layout.putConstraint(NORTH, realStackValue, 0, SOUTH, label);
        layout.putConstraint(WEST, realStackValue, 0, WEST, this);
        layout.putConstraint(EAST, realStackValue, 170, WEST, realStackValue); // weight
        layout.putConstraint(SOUTH, realStackValue, 25, NORTH, realStackValue); // height


        Dimension size = new Dimension(170, 25);

        Font plainFont = new Font("Verdana", Font.PLAIN, 12);
        Font boldFont = new Font("Verdana", Font.BOLD, 12);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        label.setSize(size);
        label.setMaximumSize(size);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(boldFont);
        label.setOpaque(true);

        realStackValue.setBorder(solidBorder);
        realStackValue.setSize(size);
        realStackValue.setMaximumSize(size);
        realStackValue.setHorizontalAlignment(JLabel.CENTER);
        realStackValue.setFont(plainFont);
        realStackValue.setOpaque(true);


    }

    public void setRealStackValue(String value) {
        realStackValue.setText(value);
    }

}
