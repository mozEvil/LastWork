package ru.mozevil.controller.parser;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.mozevil.controller.debug.DebugSaver;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class OCRParser {

    private static final Logger log = Logger.getLogger(OCRParser.class.getName());
    private static final DebugSaver ds = new DebugSaver("src\\main\\resources\\debug\\ocr\\");

    private final Tesseract tesseract;

    public OCRParser() {
        tesseract = new Tesseract();
        tesseract.setDatapath("src\\main\\resources\\tessdata");
        tesseract.setTessVariable("user_defined_dpi", "96");
        tesseract.setTessVariable("tessedit_char_whitelist", "0123456789.B Pot:NextLvlSingOuAIDscd-");
//        tesseract.setTessVariable("tessedit_char_blacklist", "èéìà§ùòç$£&%éÎÉÈ");
        tesseract.setPageSegMode(10);
//        tesseract.setTessVariable("tessedit_char_whitelist", "0123456789.B");
//        tesseract.setTessVariable("load_system_dawg", "false");
//        tesseract.setTessVariable("load_freq_dawg", "false");
//        tesseract.setTessVariable("user_words_suffix", "user-words");
//        tesseract.setTessVariable("user_patterns_suffix", "user-patterns");
//        tesseract.setTessVariable("language_model_penalty_non_dict_word", "0.15");
//        tesseract.setTessVariable("language_model_penalty_non_freq_dict_word", "0.1");
//        tesseract.setConfigs(Arrays.asList("bazaar"));


    }

    public int parseLevel(BufferedImage image) {
        tesseract.setTessVariable("tessedit_char_whitelist", "0123456789 Next Level");
        String result = parseString(image)
                .replace("Next", "")
                .replace("Level","")
                .trim();

        int lvl = 1;
        if (result.length() > 0) {
            try {
                lvl = Integer.parseInt(result);
                lvl--; // уменьшаем на 1, т.к. парсим место с указанием следующего уровня, а не текущего
            } catch (Exception e) {
                log.log(Level.ERROR," ERROR PARSE LVL", e);
                ds.save(image);
            }
        }
        return lvl;
    }

    public double parseStack(BufferedImage image) {
        tesseract.setTessVariable("tessedit_char_whitelist", "0123456789.B"
                + "All-In" + "Sitting Out" + "Disconnected" );
//        BufferedImage buf = ImageHelper.convertImageToBinary(ImageHelper.invertImageColor(image));
        BufferedImage buf = getImgReadyForOCR(image);
        BufferedImage cut = cutImg(buf);
        String result = parseString(cut).replace("B", "").trim();

        if (result.contains("All-In")) return 0;
        if (result.contains("Sitting Out")) return -1;
        if (result.contains("Disconnected")) return -1;

        double stack;

        try {
            stack = Double.parseDouble(result);

        } catch (Exception ignored) {
            buf = getInactiveStackImgReadyForOCR(image);
            cut = cutImg(buf);
            result = parseString(cut).replace("B", "").trim();;

            if (result.contains("All-In")) return 0;
            if (result.contains("Sitting Out")) return -1;
            if (result.contains("Disconnected")) return -1;

            try {
                stack = Double.parseDouble(result);

            } catch (Exception e) {
                log.log(Level.ERROR,"ERROR PARSE STACK", e);
                stack = -2;
                ds.save(image);
            }
        }
        return stack;
    }

    public double parseBet(BufferedImage image) {
        if (image == null) return 0.0;
        tesseract.setTessVariable("tessedit_char_whitelist", "0123456789.B");
//        BufferedImage buf = ImageHelper.convertImageToBinary(ImageHelper.invertImageColor(image));
        BufferedImage buf = getImgReadyForOCR(image);
        BufferedImage cut = cutImg(buf);
        String result = parseString(cut).replace("B", "").trim();
        double bet = 0;
        if (result.length() > 0) {
            try {
                bet = Double.parseDouble(result);

            } catch (Exception e) {
                log.log(Level.ERROR," ERROR PARSE BET", e);
                bet = -2;
                ds.save(image);
            }
        }
        return bet;
    }

    public double parsePot(BufferedImage image) {
        tesseract.setTessVariable("tessedit_char_whitelist", "0123456789.B Pot:");
//        BufferedImage buf = ImageHelper.convertImageToBinary(ImageHelper.invertImageColor(image));
        BufferedImage buf = getImgReadyForOCR(image);
        String result = parseString(buf)
                .replace("Pot:", "")
                .replace("B", "")
                .trim();

        double value = 0;
        try {
            value = Double.parseDouble(result);

        } catch (Exception e) {
            log.log(Level.ERROR," ERROR PARSE POT", e);
            value = -2;
            ds.save(image);
        }
        return value;
    }

    public String parseString(BufferedImage image) {
        String result = "";
        try {
            result = tesseract.doOCR(image);
        } catch (TesseractException e) {
            log.log(Level.ERROR,"ERROR PARSE STRING", e);
            ds.save(image);
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

    /** обрезает черно-белую картинку по черным пикселям слева и справа*/
    public BufferedImage cutImg(BufferedImage image) {
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (image.getRGB(x, y) == -16777216) {
                    return cutEndImg(image.getSubimage(x, 0, image.getWidth() - x, image.getHeight()));
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
        return convertToBinary(convertToMoreLight(image));
    }

    public BufferedImage getImgReadyForOCR(BufferedImage image) {
        return invertBlackAndWhite(convertToBinary(image));
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
    public BufferedImage convertToBinary(BufferedImage colorImage) {
        BufferedImage blackWhite = new BufferedImage(colorImage.getWidth(), colorImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2d = blackWhite.createGraphics();
        g2d.drawImage(colorImage, 0, 0, null);
        g2d.dispose();

        return blackWhite;
    }

    /** инвертирует черный и белый цвета */
    public BufferedImage invertBlackAndWhite(BufferedImage image) {
        BufferedImage imageOut = getBlackImage(image.getWidth(),
                image.getHeight());
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
