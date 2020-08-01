package ru.mozevil.controller.robot;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.mozevil.controller.strategy.SnG_45_simple;
import ru.mozevil.model.Bet;
import ru.mozevil.model.Decision;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class RobotKey implements PokerRobot {

    private static final Logger log = Logger.getLogger(RobotKey.class.getName());

    private Robot robot;

    public RobotKey() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            log.log(Level.ERROR,"Robot", e);
        }
    }

    @Override
    public BufferedImage grabScreen() {
        try {
            return new Robot().createScreenCapture(
                    new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())
            );
        } catch (SecurityException | AWTException e) {
            log.log(Level.ERROR,"Error grabScreen", e);
        }
        return null;
    }

    @Override
    public void makeMove(Decision decision) {
        switch (decision.getMove()) {
            case FOLD:
                pressCheckFold();
                break;
            case CHECK:
            case CALL:
                pressCheckCall();
                break;
            case RAISE:
                pressRaise(decision.getValue());
                break;
        }
    }

    private void pressCheckFold() {
        thinking();
        robot.keyPress(KeyEvent.VK_X);
        robot.delay(12);
        robot.keyRelease(KeyEvent.VK_X);
        robot.delay(120);
        robot.keyPress(KeyEvent.VK_F);
        robot.delay(12);
        robot.keyRelease(KeyEvent.VK_F);
    }

    private void pressCheckCall() {
        thinking();
        robot.keyPress(KeyEvent.VK_C);
        robot.delay(12);
        robot.keyRelease(KeyEvent.VK_C);
    }

    private void pressRaise(Bet value) {
        thinking();
        setAmount(value);
        robot.delay(120);
        robot.keyPress(KeyEvent.VK_R);
        robot.delay(12);
        robot.keyRelease(KeyEvent.VK_R);
    }

    private void setAmount(Bet value) {
        //TODO setAmount
        switch (value) {
            case ALL_IN:
                robot.keyPress(KeyEvent.VK_ALT);
                robot.delay(12);
                robot.keyPress(KeyEvent.VK_0);
                robot.delay(12);
                robot.keyRelease(KeyEvent.VK_0);
                robot.delay(12);
                robot.keyRelease(KeyEvent.VK_ALT);
                break;
            case POT_100:
                robot.keyPress(KeyEvent.VK_ALT);
                robot.delay(12);
                robot.keyPress(KeyEvent.VK_5);
                robot.delay(12);
                robot.keyRelease(KeyEvent.VK_5);
                robot.delay(12);
                robot.keyRelease(KeyEvent.VK_ALT);
                break;
            default:
                robot.keyPress(KeyEvent.VK_ALT);
                robot.delay(12);
                robot.keyPress(KeyEvent.VK_2);
                robot.delay(12);
                robot.keyRelease(KeyEvent.VK_2);
                robot.delay(12);
                robot.keyRelease(KeyEvent.VK_ALT);
                break;
        }

    }

    private void thinking() {
        robot.delay((int) (1000 + 5000*Math.random()));
    }

}
