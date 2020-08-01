package ru.mozevil.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class PanelPosition extends JPanel {

    private JLabel positionValue;

    public PanelPosition() {
        init();
    }

    private void init() {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(170, 50);

        JLabel position = new JLabel("Position");

        positionValue = new JLabel();

        add(position);

        layout.putConstraint(NORTH, position, 0, NORTH, this);
        layout.putConstraint(WEST, position, 0, WEST, this);
        layout.putConstraint(EAST, position, 170, WEST, position); // weight
        layout.putConstraint(SOUTH, position, 25, NORTH, position); // height

        add(positionValue);

        layout.putConstraint(NORTH, positionValue, 0, SOUTH, position);
        layout.putConstraint(WEST, positionValue, 0, WEST, this);
        layout.putConstraint(EAST, positionValue, 170, WEST, positionValue); // weight
        layout.putConstraint(SOUTH, positionValue, 25, NORTH, positionValue); // height


        Dimension size = new Dimension(170, 25);

        Font plainFont = new Font("Verdana", Font.PLAIN, 12);
        Font boldFont = new Font("Verdana", Font.BOLD, 12);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        position.setSize(size);
        position.setMaximumSize(size);
        position.setHorizontalAlignment(JLabel.CENTER);
        position.setFont(boldFont);
        position.setOpaque(true);

        positionValue.setBorder(solidBorder);
        positionValue.setSize(size);
        positionValue.setMaximumSize(size);
        positionValue.setHorizontalAlignment(JLabel.CENTER);
        positionValue.setFont(plainFont);
        positionValue.setOpaque(true);


    }

    public void setPositionValue(String value) {
        positionValue.setText(value);
    }

}
