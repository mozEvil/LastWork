package ru.mozevil.controller.robot;

import ru.mozevil.model.Decision;

import java.awt.image.BufferedImage;

public interface PokerRobot {

    BufferedImage grabScreen();

    void makeMove(Decision decision);
}
