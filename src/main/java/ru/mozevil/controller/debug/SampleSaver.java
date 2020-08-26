package ru.mozevil.controller.debug;

import ru.mozevil.controller.parser.cutter.Cutter;
import ru.mozevil.controller.parser.cutter.TableCutter1024;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SampleSaver {

    public static void main(String[] args) throws IOException {
        int in = 0;

//        BufferedImage img = ImageIO.read(new File("src\\main\\resources\\samples\\tables\\!\\" + in +".png"));
        Cutter cutter = new TableCutter1024();
//        cutter.setImageTable(img);
        int n = 9;

        for (int i = 0; i < 9; i++) {
            BufferedImage img = ImageIO.read(new File("src\\main\\resources\\samples\\tables\\!\\" + i +".png"));
            cutter.setImageTable(img);
            BufferedImage sample = cutter.getDealerImg(i);
            String out = "src\\main\\resources\\samples\\dealer\\d" + (i) + ".png";
            ImageIO.write(sample, "png", new File(out));
        }
    }
}
