package ru.mozevil.controller.debug;

import net.sourceforge.tess4j.util.ImageHelper;
import ru.mozevil.controller.parser.OCRParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DebugOCR {

    public static void main(String[] args) throws IOException {

        String path = "C:\\git_repo\\SomePro\\src\\main\\resources\\debug\\ocr\\";

        String name = "test1";

        OCRParser ocr = new OCRParser();
        BufferedImage in = ImageIO.read(new File(path + name + ".png"));


        System.out.print("String: " + ocr.parseString(in));
        System.out.print("ImageHelper: " + ocr.parseString(ImageHelper.convertImageToBinary(ImageHelper.invertImageColor(in))));
        System.out.print("Ready: " + ocr.parseString(ocr.cutImg(ocr.getImgReadyForOCR(in))));
        System.out.print("inactive: " + ocr.parseString(ocr.cutImg(ocr.getInactiveStackImgReadyForOCR(in))));
        System.out.print("Parse: " + ocr.parseStack(in));

    }
}
