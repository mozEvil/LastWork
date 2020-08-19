package ru.mozevil.controller.parser;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OCRParser {

    private static final Logger log = Logger.getLogger(OCRParser.class.getName());

    private final Tesseract tesseract;

    public OCRParser() {
        tesseract = new Tesseract();
        tesseract.setDatapath("src\\main\\resources\\tessdata");
    }

    public int parseLevel(BufferedImage image) {
        String result = parseString(image);
        result = result.replace("Next Level", "").trim();

        int lvl = 1;
        if (result.length() > 0) {
            try {
                lvl = Integer.parseInt(result);
                lvl--; // уменьшаем на 1, т.к. парсим место с указанием следующего уровня, а не текущего
            } catch (Exception e) {
                log.log(Level.ERROR," ERROR PARSE LVL", e);
            }
        }
        return lvl;
    }

    public double parseStack(BufferedImage image) {
        BufferedImage readyImg = getImgReadyForOCR(image);
        BufferedImage cutReadyImg = cutImg(readyImg);
        String result = parseString(cutReadyImg);

        if (result.length() == 0) {
            readyImg = getInactiveStackImgReadyForOCR(image);
            cutReadyImg = cutImg(readyImg);
            result = parseString(cutReadyImg);
        }

        if (result.contains("Sitting Out")) return -1;
        if (result.contains("Disconnected")) return -1;
        if (result.contains("All-In")) return 0;

        result = result.replace("B", "").trim();

        double stack;
        try {
            stack = Double.parseDouble(result);
        } catch (Exception e) {
            log.log(Level.ERROR,"ERROR PARSE STACK", e);
            stack = -2;
        }
        return stack;
    }

    public double parseBet(BufferedImage image) {
        BufferedImage readyImg = getImgReadyForOCR(image);
        BufferedImage cutReadyImg = cutImg(readyImg);
        String result = parseString(cutReadyImg).replace("B", "").trim();

        double bet = 0;
        if (result.length() > 0) {
            try {
                bet = Double.parseDouble(result);

            } catch (Exception e) {
                log.log(Level.ERROR," ERROR PARSE BET", e);
                bet = -2;
            }
        }
        return bet;
    }

    /** обрезает черно-белую картинку по черным пикселям слева и справа*/
    public BufferedImage cutImg(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getRGB(x, y) == -16777216) {
                    return cutEndImg(
                            image.getSubimage(x, 0, image.getWidth() - x, image.getHeight())
                    );
                }
            }
        }
        return image;
    }

    private BufferedImage cutEndImg(BufferedImage image) {
        for (int x = image.getWidth() - 1; x > 0; x--) {
            for (int y = 0; y < image.getHeight() ; y++) {
                if (image.getRGB(x, y) == -16777216) {
                    return image.getSubimage(0, 0, (x + 1), image.getHeight());
                }
            }
        }
        return image;
    }

    public double parsePot(BufferedImage image) {
        String result = parseString(getImgReadyForOCR(image));
        result = result.replace("Pot:", "");
        result = result.replace("BB", "");

        double value = 0;
        try {
            value = Double.parseDouble(result.trim());

        } catch (Exception e) {
            log.log(Level.ERROR," ERROR PARSE POT", e);
            value = -2;
        }
        return value;
    }

    public String parseString(BufferedImage image) {
        String result = "";
        try {
            result = tesseract.doOCR(image);
        } catch (TesseractException e) {
            log.log(Level.ERROR,"ERROR PARSE STRING", e);
        }
        return result;
    }

    /** @return X*/
    private int findImg(BufferedImage imageWhatNeedFind, BufferedImage imageWhereNeedFind) {
        for (int i = 0; i < imageWhereNeedFind.getWidth() - imageWhatNeedFind.getWidth(); i++) {
            BufferedImage pieceImg = imageWhereNeedFind.getSubimage(i, 0,
                    imageWhatNeedFind.getWidth(), imageWhereNeedFind.getHeight());
            if (imageCompare(pieceImg, imageWhatNeedFind)) return i;
        }
        System.out.println(" не нашел совпадения");
        return -1;
    }

    private boolean imageCompare(BufferedImage image1, BufferedImage image2) {
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

    public BufferedImage getInactiveStackImgReadyForOCR(BufferedImage image) {
        return convertToBlackWhite(convertToMoreLight(image));
    }

    public BufferedImage getImgReadyForOCR(BufferedImage image) {
        return invertBlackAndWhite(convertToBlackWhite(image));
    }

    /** делает изображение более ярким (используется для изменения результатов конвертации в бинарное изображение)*/
    public BufferedImage convertToMoreLight(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = result.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        for (int x = 0; x < result.getWidth(); x++) {
            for (int y = 0; y < result.getHeight(); y++) {
                int rbg = result.getRGB(x, y) - 5005035; //5005035
                result.setRGB(x, y, rbg);
            }
        }
        return result;
    }

    public BufferedImage convertToGrayscale(BufferedImage colorImage) {
        BufferedImage grayscale = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D g2d = grayscale.createGraphics();
        g2d.drawImage(colorImage, 0, 0, null);
        g2d.dispose();

        return grayscale;
    }

    /** конвертирует изображенеи в бинарное черно-белое*/
    public BufferedImage convertToBlackWhite(BufferedImage colorImage) {
        BufferedImage blackWhite = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2d = blackWhite.createGraphics();
        g2d.drawImage(colorImage, 0, 0, null);
        g2d.dispose();

        return blackWhite;
    }

    /** инвертирует черный и белый цвета */
    public BufferedImage invertBlackAndWhite(BufferedImage image) {
        BufferedImage imageOut = getBlackImage(image.getWidth(),
                image.getHeight());//from  ww  w .j a  v  a  2  s  . com
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color c = new Color(image.getRGB(i, j));
                //invert white into black
                if (c.equals(Color.white)) {
                    imageOut.setRGB(i, j, Color.black.getRGB());
                }
                //invert black into white
                else if (c.equals(Color.black)) {
                    imageOut.setRGB(i, j, Color.white.getRGB());
                }
                //copy the other colors
                else {
                    imageOut.setRGB(i, j, image.getRGB(i, j));
                }
            }
        }
        return imageOut;
    }

    private BufferedImage getBlackImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0; i < width - 1; i++) {
            for (int j = 0; j < height - 1; j++) {
                image.setRGB(i, j, Color.black.getRGB());
            }
        }
        return image;
    }

}
