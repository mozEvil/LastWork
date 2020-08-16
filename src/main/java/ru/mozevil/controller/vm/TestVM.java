package ru.mozevil.controller.vm;

public class TestVM {

    public static void main(String[] args) {

        VMoze vm = new VMoze("Win7");
        vm.connect();
        vm.openSession();

        vm.keyboardPut(KeyCode.PRESS_1);
        vm.keyboardPut(KeyCode.RELEASE_1);
        vm.keyboardPut(KeyCode.PRESS_2);
        vm.keyboardPut(KeyCode.RELEASE_2);

        vm.mouseMoveFor(50, 50);

        vm.closeSession();
        vm.disconnect();
    }
}
