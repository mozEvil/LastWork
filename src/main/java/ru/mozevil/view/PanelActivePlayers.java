package ru.mozevil.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static javax.swing.SpringLayout.*;

public class PanelActivePlayers extends JPanel {

    private JLabel ActivePlayersValue;

    public PanelActivePlayers() {
        init();
    }

    private void init() {
        setOpaque(false);

        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        setSize(170, 50);

        JLabel activePlayers = new JLabel("Active players count");

        ActivePlayersValue = new JLabel();

        add(activePlayers);

        layout.putConstraint(NORTH, activePlayers, 0, NORTH, this);
        layout.putConstraint(WEST, activePlayers, 0, WEST, this);
        layout.putConstraint(EAST, activePlayers, 170, WEST, activePlayers); // weight
        layout.putConstraint(SOUTH, activePlayers, 25, NORTH, activePlayers); // height

        add(ActivePlayersValue);

        layout.putConstraint(NORTH, ActivePlayersValue, 0, SOUTH, activePlayers);
        layout.putConstraint(WEST, ActivePlayersValue, 0, WEST, this);
        layout.putConstraint(EAST, ActivePlayersValue, 170, WEST, ActivePlayersValue); // weight
        layout.putConstraint(SOUTH, ActivePlayersValue, 25, NORTH, ActivePlayersValue); // height


        Dimension size = new Dimension(170, 25);

        Font plainFont = new Font("Verdana", Font.PLAIN, 12);
        Font boldFont = new Font("Verdana", Font.BOLD, 12);

        Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        activePlayers.setSize(size);
        activePlayers.setMaximumSize(size);
        activePlayers.setHorizontalAlignment(JLabel.CENTER);
        activePlayers.setFont(boldFont);
        activePlayers.setOpaque(true);

        ActivePlayersValue.setBorder(solidBorder);
        ActivePlayersValue.setSize(size);
        ActivePlayersValue.setMaximumSize(size);
        ActivePlayersValue.setHorizontalAlignment(JLabel.CENTER);
        ActivePlayersValue.setFont(plainFont);
        ActivePlayersValue.setOpaque(true);


    }

    public void setActivePlayersValue(String value) {
        ActivePlayersValue.setText(value);
    }

}
