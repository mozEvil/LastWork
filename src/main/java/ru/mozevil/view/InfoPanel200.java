package ru.mozevil.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class InfoPanel200 extends JPanel {

    private final JLabel value;

    public InfoPanel200(String name) {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(200, 50);

        JLabel label = new JLabel(name);

        value = new JLabel();

        add(label);

        layout.putConstraint(NORTH, label, 0, NORTH, this);
        layout.putConstraint(WEST, label, 10, WEST, this);
        layout.putConstraint(EAST, label, 180, WEST, label); // weight
        layout.putConstraint(SOUTH, label, 25, NORTH, label); // height

        add(value);

        layout.putConstraint(NORTH, value, 25, NORTH, this);
        layout.putConstraint(WEST, value, 10, WEST, this);
        layout.putConstraint(EAST, value, 180, WEST, value); // weight
        layout.putConstraint(SOUTH, value, 25, NORTH, value); // height

        Font plainFont = new Font("Verdana", Font.PLAIN, 12);
        Font boldFont = new Font("Verdana", Font.BOLD, 12);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(boldFont);
        label.setOpaque(true);

        value.setBorder(solidBorder);
        value.setHorizontalAlignment(JLabel.CENTER);
        value.setFont(plainFont);
        value.setOpaque(true);
    }

    public void update(String value) {
        this.value.setText(value);
    }

}
