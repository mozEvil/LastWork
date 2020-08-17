package ru.mozevil.controller.robot;

import ru.mozevil.model.Environment;

import java.awt.image.BufferedImage;

public interface PokerRobot {

    BufferedImage grabScreen();

    void makeMove(Environment env);
}
