package ru.mozevil.controller.robot;

import ru.mozevil.controller.robot.vm.KeyCode;
import ru.mozevil.controller.robot.vm.VMoze;
import ru.mozevil.model.Bet;
import ru.mozevil.model.Environment;
import ru.mozevil.model.Move;

import java.awt.image.BufferedImage;

public class RobotVM implements PokerRobot {

    private final VMoze vm;

    public RobotVM(VMoze vm) {
        this.vm = vm;
    }

    @Override
    public BufferedImage grabScreen() {
        return vm.getScreenShot();
    }

    @Override
    public void makeMove(Environment env) {

        setBetValue(env.getDecision().getValue());

        makeMove(env.getDecision().getMove());
    }

    private boolean setBetValue(Bet bet) {
        switch (bet) {
            case NULL: return true;
            case BB_2: return press_Shift_Key(KeyCode.PRESS_1);
            case BB_2_25: return press_Alt_Shift_Key(KeyCode.PRESS_1);
            case BB_2_5: return press_Shift_Key(KeyCode.PRESS_2);
            case BB_3: return press_Shift_Key(KeyCode.PRESS_3);
            case BB_4: return press_Shift_Key(KeyCode.PRESS_4);
            case BB_5: return press_Shift_Key(KeyCode.PRESS_5);
            case BB_6: return press_Shift_Key(KeyCode.PRESS_6);
            case BB_7: return press_Shift_Key(KeyCode.PRESS_7);
            case BB_8: return press_Shift_Key(KeyCode.PRESS_8);
            case BB_9: return press_Shift_Key(KeyCode.PRESS_9);
            case BB_10: return press_Shift_Key(KeyCode.PRESS_0);
            case BB_12: return press_Shift_Key(KeyCode.PRESS_MINUS);
            case BB_15: return press_Shift_Key(KeyCode.PRESS_EQUALS);
            case ALL_IN: return press_Alt_Key(KeyCode.PRESS_0);
            case POT_33: return press_Alt_Key(KeyCode.PRESS_1);
            case POT_50: return press_Alt_Key(KeyCode.PRESS_2);
            case POT_66: return press_Alt_Key(KeyCode.PRESS_3);
            case POT_75: return press_Alt_Key(KeyCode.PRESS_4);
            case POT_100: return press_Alt_Key(KeyCode.PRESS_5);
            default: return true;
        }
    }

    private boolean makeMove(Move move) {
        switch (move) {
            case FOLD: return pressKey(KeyCode.PRESS_F);
            case CHECK: return pressKey(KeyCode.PRESS_X);
            case CALL: return pressKey(KeyCode.PRESS_C);
            case RAISE: return pressKey(KeyCode.PRESS_R);
            default: return true;
        }
    }

    private boolean pressKey(int keyCode) {
        vm.keyboardPut(keyCode); // press
        vm.keyboardPut(keyCode + 0x80); // release
        return true; // only for simplify switch look
    }

    private boolean press_Alt_Key(int keyCode) {
        vm.keyboardPut(KeyCode.PRESS_ALT);
        vm.keyboardPut(keyCode); // press
        vm.keyboardPut(keyCode + 0x80); // release
        vm.keyboardPut(KeyCode.RELEASE_ALT);
        return true; // only for simplify switch look
    }

    private boolean press_Shift_Key(int keyCode) {
        vm.keyboardPut(KeyCode.PRESS_SHIFT);
        vm.keyboardPut(keyCode); // press
        vm.keyboardPut(keyCode + 0x80); // release
        vm.keyboardPut(KeyCode.RELEASE_SHIFT);
        return true; // only for simplify switch look
    }

    private boolean press_Alt_Shift_Key(int keyCode) {
        vm.keyboardPut(KeyCode.PRESS_ALT);
        vm.keyboardPut(KeyCode.PRESS_SHIFT);
        vm.keyboardPut(keyCode); // press
        vm.keyboardPut(keyCode + 0x80); // release
        vm.keyboardPut(KeyCode.RELEASE_SHIFT);
        vm.keyboardPut(KeyCode.RELEASE_ALT);
        return true; // only for simplify switch look
    }
}
