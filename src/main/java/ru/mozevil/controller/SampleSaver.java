package ru.mozevil.controller;

import ru.mozevil.controller.parser.cutter.Cutter;
import ru.mozevil.controller.parser.cutter.TableCutter1024;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SampleSaver {

    public static void main(String[] args) throws IOException {

        BufferedImage img = ImageIO.read(new File("src\\main\\resources\\samples\\tables\\!\\12.png"));
        Cutter cutter = new TableCutter1024();
        cutter.setImageTable(img);
        int n = 9;

//        for (int i = 1; i <= 4; i++) {
//            if (i == 2 || i == 3) continue;
            BufferedImage sample = cutter.getDealerImg(2);
            String out = "src\\main\\resources\\samples\\dealer\\d" + (n++) + ".png";
            ImageIO.write(sample, "png", new File(out));
//        }
    }
}
