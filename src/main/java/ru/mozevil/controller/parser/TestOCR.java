package ru.mozevil.controller.parser;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import ru.mozevil.model.Table;
import ru.mozevil.model.TableMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.BatchUpdateException;

public class TestOCR {

    public static void main(String[] args) throws IOException {

        int num = 112;

//        for (int j = 1; j < 127; j++) {
//            System.out.println(" Image " + j);
        BufferedImage table = ImageIO.read(new File("src\\main\\resources\\samples\\tables\\a\\"+ num +".png"));

        OCRParser ocr = new OCRParser();
        TableCutter cutter = new TableCutter();

        cutter.setImageTable(table);

        BufferedImage cutImg = cutter.getLvlImg();
        String lvlStr = ocr.parseString(cutImg);

        SampleSaver.saveSample(cutImg, "src\\main\\resources\\samples\\tables\\!\\"+ 1 +".png", "png");
        System.out.println(lvlStr);
        System.out.println(ocr.parseLevel(cutImg));

//        }

///////////////
//        int seat = 0;
//        BufferedImage r = cutter.getBetSizeImg(seat);
//        BufferedImage r1 = ocr.getImgReadyForOCR(r);
//        BufferedImage r2 = ocr.cutImg(r1);
//        BufferedImage r3 = cutter.getBetSizeFullImg(seat);
//
//        SampleSaver.saveSample(r, "src\\main\\resources\\samples\\tables\\!\\"+ 1 +".png", "png");
//        SampleSaver.saveSample(r1, "src\\main\\resources\\samples\\tables\\!\\"+ 12 +".png", "png");
//        SampleSaver.saveSample(r2, "src\\main\\resources\\samples\\tables\\!\\"+ 112 +".png", "png");
//        SampleSaver.saveSample(r3, "src\\main\\resources\\samples\\tables\\!\\"+ 111 +".png", "png");

//        int pxCut = 2;
//        BufferedImage r3 = r1.getSubimage(pxCut, 0, r1.getWidth() - pxCut, r1.getHeight());
//        System.out.println("Cut " + pxCut + " px: " + ocr.parseString(r3));
//        SampleSaver.saveSample(r3, "src\\main\\resources\\samples\\tables\\!\\"+ 1113 +".png", "png");

//        System.out.println("Just string: " + ocr.parseString(r));
//        System.out.println("Ready img string: " + ocr.parseString(r1));
//        System.out.println("Bet: " + ocr.parseBet(r));
//        System.out.println("Pot : " + ocr.parsePot(cutter.getPotImg()));

///////////
//        BufferedImage img = r1.getSubimage(0, 9, 20, 7);
//        SampleSaver.saveSample(img, "src\\main\\resources\\samples\\tables\\!\\"+ 2 +".png", "png");
//
//        for (int i = 0; i < img.getWidth(); i++) {
//            System.out.println(img.getRGB(i, 0));
//        }

////////////////////


    }


}
