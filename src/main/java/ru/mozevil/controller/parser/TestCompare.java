package ru.mozevil.controller.parser;

import ru.mozevil.model.TableMap;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestCompare {

    public static void main(String[] args) throws IOException {

        CompareParser parser = new CompareParser();
        TableCutter cutter = new TableCutter();

        cutter.setImageTable(ImageIO.read(new File("src\\main\\resources\\samples\\tables\\b\\4.png")));

        for (int i = 1; i < 9; i++) {
//            System.out.println(i + " - " + parser.isEmptySeat(cutter.getSeatImg(i)));
            System.out.println(i + " - " + parser.isActivePlayer(cutter.getActivePlayerImg(i)));
        }

    }

    private static boolean imageCompare(BufferedImage image1, BufferedImage image2) {

        if (image1 == null || image2 == null) return false;
        if (image1.getWidth() != image2.getWidth()) return false;
        if (image1.getHeight() != image2.getHeight()) return false;

        for (int x = 0; x < image1.getWidth(); x++) {
            for (int y = 0; y < image1.getHeight(); y++) {
                if (image1.getRGB(x, y) != image2.getRGB(x, y)) return false;
            }
        }

        return true;
    }

    private static BufferedImage cutTable(BufferedImage imageTable, TableMap tableMap) {
        return imageTable.getSubimage(tableMap.getX(), tableMap.getY(), tableMap.getW(), tableMap.getH());
    }
}
