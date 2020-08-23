package ru.mozevil.controller;

import ru.mozevil.controller.parser.PokerParser;
import ru.mozevil.controller.parser.TableParser;
import ru.mozevil.controller.robot.PokerRobot;
import ru.mozevil.controller.robot.RobotVM;
import ru.mozevil.controller.robot.vm.VMoze;
import ru.mozevil.model.Environment;
import ru.mozevil.model.factory.DF;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Grabber implements Runnable {

    private VMoze vm;
    private int name = 0;
    private PokerParser parser;
    private PokerRobot robot;

    public Grabber(VMoze vm) {
        this.vm = vm;
        robot = new RobotVM(vm);
        parser = new TableParser();
    }

    @Override
    public void run() {
        BufferedImage img = vm.getScreenShot();
        parser.setImageTable(img);

        if (parser.isAction()) {
            try {
                File out = new File("src\\main\\resources\\samples\\tables\\!\\a\\" + ++name + ".png");
                ImageIO.write(img, "png", out);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Environment env = new Environment();
            env.setDecision(DF.check_fold());
            robot.makeMove(env);
        }
    }
}
