package ru.mozevil.controller.robot;

import ru.mozevil.model.Decision;
import ru.mozevil.model.Environment;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class RobotMouse implements PokerRobot {

    private Robot robot;

    public RobotMouse() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BufferedImage grabScreen() {
        try {
            return new Robot().createScreenCapture(
                    new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())
            );
        } catch (SecurityException | AWTException e) {
            System.out.println("Error grabScreen");
        }
        return null;
    }

    @Override
    public void makeMove(Environment env) {
        switch (env.getDecision().getMove()) {
            case FOLD:
                pressFold();
                break;
            case CHECK:
            case CALL:
                pressCheckCall();
                break;
            case RAISE:
              //  pressRaise(decision.getValue());
                break;
        }
    }

    private void pressFold() {
        // 697, 677 - FOLD [200x50]
        int x = (int) (720 + 160*Math.random());
        int y = (int) (680 + 40*Math.random());
        mouseClick(x, y);
    }

    private void pressCheckCall() {
        // 922, 677 - CHECK/CALL [200x50]
        int x = (int) (950 + 160*Math.random());
        int y = (int) (680 + 40*Math.random());
        mouseClick(x, y);
    }

    private void pressRaise(double value) {

        setValue(value);

        // 1148, 677 - RAISE [200x50]
        int x = (int) (1170 + 160*Math.random()); // кнопка рейз
        int y = (int) (680 + 40*Math.random()); // кнопка рейз
        mouseClick(x, y);
    }

    private void pressAllIn() {
        // 1275, 592 - MAX [70, 20]
        int x = (int) (1275 + 70*Math.random()); // мини кнопка max
        int y = (int) (592 + 20*Math.random()); // мини кнопка max
        mouseClick(x, y);

        // 1148, 677 - RAISE [200x50]
        x = (int) (1170 + 160*Math.random()); // кнопка рейз
        y = (int) (680 + 40*Math.random()); // кнопка рейз
        mouseClick(x, y);
    }

    private void mouseClick(int x, int y) {
//        Point location = MouseInfo.getPointerInfo().getLocation();
//        int xNow = location.x;
//        int yNow = location.y;

        robot.mouseMove(x, y);
        robot.delay((int) (100 + 400*Math.random()));
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay((int) (30 + 100*Math.random()));
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void setValue(double value) {
        // 930, 637  - value [70, 15]
        int x = (int) (930 + 70*Math.random()); // поле ввода размера ставки
        int y = (int) (637 + 15*Math.random()); // поле ввода размера ставки
        mouseClick(x, y);

        // выделяем текущий текст в поле размера ставки
        robot.delay((int) (100 + 200*Math.random()));
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay((int) (20 + 120*Math.random()));
        robot.keyPress(KeyEvent.VK_A);
        robot.delay((int) (10 + 30*Math.random()));
        robot.keyRelease(KeyEvent.VK_A);
        robot.delay((int) (20 + 120*Math.random()));
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay((int) (150 + 350*Math.random()));

        // парсим какие клавиши нужно нажать, чтобы напечатать нужный нам размер ставки
        int[] keys = parseKeys(value);

        // печатаем сумму ставки
        for (int i = 0; i < keys.length; i++) {
            robot.keyPress(keys[i]);
            robot.delay((int) (10 + 30*Math.random()));
            robot.keyRelease(keys[i]);
            robot.delay((int) (100 + 300*Math.random()));
        }
    }

    private int[] parseKeys(double value) {
        char[] sum = String.valueOf(value).toCharArray();
        int[] keys = new int[sum.length];

        for (int i = 0; i < sum.length; i++) {
            keys[i] = parseKey(sum[i]);
        }

        return keys;
    }

    private int parseKey(char c) {
        HashMap<Character, Integer> keyMap = new HashMap<>();
        keyMap.put('0', KeyEvent.VK_0);
        keyMap.put('1', KeyEvent.VK_1);
        keyMap.put('2', KeyEvent.VK_2);
        keyMap.put('3', KeyEvent.VK_3);
        keyMap.put('4', KeyEvent.VK_4);
        keyMap.put('5', KeyEvent.VK_5);
        keyMap.put('6', KeyEvent.VK_6);
        keyMap.put('7', KeyEvent.VK_7);
        keyMap.put('8', KeyEvent.VK_8);
        keyMap.put('9', KeyEvent.VK_9);
        keyMap.put('.', KeyEvent.VK_DECIMAL);

        return keyMap.get(c);
    }
}
