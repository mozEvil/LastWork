package ru.mozevil.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class PanelIOP extends JPanel {

    private JLabel iopValue;

    public PanelIOP() {
        init();
    }

    private void init() {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(170, 50);

        JLabel iop = new JLabel("IOP");

        iopValue = new JLabel();

        add(iop);

        layout.putConstraint(NORTH, iop, 0, NORTH, this);
        layout.putConstraint(WEST, iop, 0, WEST, this);
        layout.putConstraint(EAST, iop, 170, WEST, iop); // weight
        layout.putConstraint(SOUTH, iop, 25, NORTH, iop); // height

        add(iopValue);

        layout.putConstraint(NORTH, iopValue, 0, SOUTH, iop);
        layout.putConstraint(WEST, iopValue, 0, WEST, this);
        layout.putConstraint(EAST, iopValue, 170, WEST, iopValue); // weight
        layout.putConstraint(SOUTH, iopValue, 25, NORTH, iopValue); // height


        Dimension size = new Dimension(170, 25);

        Font plainFont = new Font("Verdana", Font.PLAIN, 12);
        Font boldFont = new Font("Verdana", Font.BOLD, 12);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        iop.setSize(size);
        iop.setMaximumSize(size);
        iop.setHorizontalAlignment(JLabel.CENTER);
        iop.setFont(boldFont);
        iop.setOpaque(true);

        iopValue.setBorder(solidBorder);
        iopValue.setSize(size);
        iopValue.setMaximumSize(size);
        iopValue.setHorizontalAlignment(JLabel.CENTER);
        iopValue.setFont(plainFont);
        iopValue.setOpaque(true);


    }

    public void setIopValue(String value) {
        iopValue.setText(value);
    }

}
