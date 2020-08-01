package ru.mozevil.controller.grabber;

import ru.mozevil.controller.parser.PokerParser;
import ru.mozevil.controller.parser.TableParser;
import ru.mozevil.controller.robot.RobotKey;
import ru.mozevil.controller.robot.PokerRobot;
import ru.mozevil.model.Decision;
import ru.mozevil.model.Move;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenGrabber implements Runnable {

    private PokerRobot robot = new RobotKey();
    private PokerParser parser = new TableParser();
    private String filePath = "src\\main\\resources\\samples\\tables\\c\\";
    private int fileName = 0;
    private String fileExt = ".png";

    private boolean thinking;

    @Override
    public void run() {
        //парсим 1 раз, только если наступил ход hero
        BufferedImage image = grabScreen();
        parser.setImageTable(image);

        // если ход hero не наступил, то ничего не делаем и отмечаем, что последнее действие завершено
        if (!parser.isAction()) thinking = false;

        // если настал ход hero, делаем скриншот 1 раз.
        if (parser.isAction() && !thinking) {
            thinking = true; // началось новое действие, hero думает как сходить
            fileName++;
            try {
                ImageIO.write(image, "png", new File(filePath + fileName + fileExt));
            } catch (IOException e) {
                e.printStackTrace();
            }

            robot.makeMove(new Decision(Move.CALL));
        }
    }

    private BufferedImage grabScreen() {
        try {
            return new Robot().createScreenCapture(
                    new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())
            );
        } catch (SecurityException | AWTException e) {
            System.out.println("Error grabScreen");
        }
        return null;
    }



}
