package ru.mozevil.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class PanelStreet extends JPanel {

    private JLabel streetValue;

    public PanelStreet() {
        init();
    }

    private void init() {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(170, 50);

        JLabel street = new JLabel("Street");

        streetValue = new JLabel();

        add(street);

        layout.putConstraint(NORTH, street, 0, NORTH, this);
        layout.putConstraint(WEST, street, 0, WEST, this);
        layout.putConstraint(EAST, street, 170, WEST, street); // weight
        layout.putConstraint(SOUTH, street, 25, NORTH, street); // height

        add(streetValue);

        layout.putConstraint(NORTH, streetValue, 0, SOUTH, street);
        layout.putConstraint(WEST, streetValue, 0, WEST, this);
        layout.putConstraint(EAST, streetValue, 170, WEST, streetValue); // weight
        layout.putConstraint(SOUTH, streetValue, 25, NORTH, streetValue); // height


        Dimension size = new Dimension(170, 25);

        Font plainFont = new Font("Verdana", Font.PLAIN, 12);
        Font boldFont = new Font("Verdana", Font.BOLD, 12);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        street.setSize(size);
        street.setMaximumSize(size);
        street.setHorizontalAlignment(JLabel.CENTER);
        street.setFont(boldFont);
        street.setOpaque(true);

        streetValue.setBorder(solidBorder);
        streetValue.setSize(size);
        streetValue.setMaximumSize(size);
        streetValue.setHorizontalAlignment(JLabel.CENTER);
        streetValue.setFont(plainFont);
        streetValue.setOpaque(true);


    }

    public void setStreetValue(String value) {
        streetValue.setText(value);
    }

}
