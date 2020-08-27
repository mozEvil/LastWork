package ru.mozevil.controller.debug;

import java.awt.*;

public class ColorPicker {

    public static void main(String[] args) {

//        int argb = 0x014E19;
        int argb = 0x014C18;
        int col = -16689893;

        Color c = new Color(argb);
        Color c2 = new Color(col);
        System.out.println(c.getRGB());


//        int a = (argb>>24)&0xFF;
//        int r = (argb>>16)&0xFF;
//        int g = (argb>>8)&0xFF;
//        int b = (argb)&0xFF;
    }
}
