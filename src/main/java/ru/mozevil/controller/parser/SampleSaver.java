package ru.mozevil.controller.parser;

import ru.mozevil.model.TableMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SampleSaver {

    public static void main(String[] args) {

        BufferedImage imageTable = getImageTableByShortName("3", "png");
        TableMap map = TableMap.POT;
        String filePath = "src\\main\\resources\\samples\\pot\\3.png";
        String formatName = "png";

        BufferedImage image = convertToBlackWhite(imageTable);

        saveSample(image, map, filePath, formatName);

//        TableCutter cutter = new TableCutter();
//        cutter.setImageTable(imageTable);

//        for (int i = 4; i == 4; i++) {
//            String path = "src\\main\\resources\\samples\\seats\\s" + (i+8) +".png";
//            BufferedImage imageSample = cutter.getSeatImg(i);
//            saveSample(imageSample, path, formatName);
//        }

    }

    public static void saveSample(BufferedImage imageTable, TableMap tableMap, String filePath, String formatName) {
        try {
            BufferedImage image = imageTable.getSubimage(
                    tableMap.getX(), tableMap.getY(), tableMap.getW(), tableMap.getH());

            ImageIO.write(image, formatName, new File(filePath));

        } catch (IOException e) {
            System.out.println("ERROR SAVE SAMPLE");
        }
    }

    public static void saveSample(BufferedImage imageSample, String filePath, String formatName) {
        try {

            ImageIO.write(imageSample, formatName, new File(filePath));

        } catch (IOException e) {
            System.out.println("ERROR SAVE SAMPLE");
        }
    }

    public static BufferedImage getImageTableByShortName(String fileName, String fileExtend) {
        String pathname = "src\\main\\resources\\samples\\tables\\a\\" + fileName + "." + fileExtend;
        BufferedImage imageTable = null;
        try {
            imageTable = ImageIO.read(new File(pathname));
        } catch (IOException e) {
            System.out.println("file not found: " + fileName);
        }

        return imageTable;
    }

    private static BufferedImage convertToBlackWhite(BufferedImage colorImage) {

        BufferedImage blackWhite = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2d = blackWhite.createGraphics();
        g2d.drawImage(colorImage, 0, 0, null);
        g2d.dispose();

        return blackWhite;
    }

}
