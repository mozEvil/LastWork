package ru.mozevil.controller.vm;

public class TestVM {

    public static void main(String[] args) {

        VMoze vm = new VMoze("Win7");
        vm.connect();
        vm.openSession();

//        vm.keyboardPut(KeyCode.PRESS_SHIFT);
//        vm.keyboardPut(KeyCode.PRESS_A);
//        vm.keyboardPut(KeyCode.RELEASE_A);
//        vm.keyboardPut(KeyCode.RELEASE_SHIFT);

        vm.mouseMoveTo(50, 50);
        vm.mouseClick(MouseCode.BTN_LEFT);
        vm.mouseClick(MouseCode.BTN_LEFT);
//        vm.keyboardPut(KeyCode.PRESS_ENTER);
//        vm.keyboardPut(KeyCode.RELEASE_ENTER);


        vm.closeSession();
        vm.disconnect();
    }
}
