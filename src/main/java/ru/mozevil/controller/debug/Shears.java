package ru.mozevil.controller.debug;

import ru.mozevil.controller.parser.OCRParser;
import ru.mozevil.controller.parser.cutter.Cutter;
import ru.mozevil.controller.parser.cutter.TableCutter1024;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Shears {

    public static void main(String[] args) throws IOException {
        String path = "src\\main\\resources\\debug\\1\\";

        String name = "44";

//        OCRParser ocr = new OCRParser();
        BufferedImage in = ImageIO.read(new File(path + name + ".png"));

        Cutter cutter = new TableCutter1024();
        cutter.setImageTable(in);

        ImageIO.write(cutter.getBetSizeImg(3), "png",
                new File("src\\main\\resources\\debug\\ocr\\_1" + ".png"));

//        for (int i = 0; i < 9; i++) {
//            ImageIO.write(cutter.getBetSizeImg(i), "png", new File(path + name + "_" + i + ".png"));
//        }
    }
}
