package ru.mozevil.controller.robot;

import ru.mozevil.controller.vm.VMoze;
import ru.mozevil.model.Decision;

import java.awt.image.BufferedImage;
//todo robot VM
public class RobotVM implements PokerRobot {

    private VMoze vm;

    public RobotVM(VMoze vm) {
        this.vm = vm;
    }

    @Override
    public BufferedImage grabScreen() {
        return vm.getScreenShot();
    }


    @Override
    public void makeMove(Decision decision) {

    }
}
