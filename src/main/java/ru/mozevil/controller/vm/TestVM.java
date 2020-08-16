package ru.mozevil.controller.vm;

public class TestVM {

    public static void main(String[] args) {

        VMoze vm = new VMoze("Win7");
        vm.connect();
        vm.openSession();

        vm.keyboardPut(KeyCode.PRESS_SHIFT);
        vm.keyboardPut(KeyCode.PRESS_A);
        vm.keyboardPut(KeyCode.RELEASE_A);
        vm.keyboardPut(KeyCode.RELEASE_SHIFT);
        vm.keyboardPut(KeyCode.PRESS_ENTER);
        vm.keyboardPut(KeyCode.RELEASE_ENTER);

//        vm.mouseMoveFor(50, 50);

        vm.closeSession();
        vm.disconnect();
    }
}
