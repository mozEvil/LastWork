package ru.mozevil.controller.parser;

import ru.mozevil.model.Card;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class CompareParser {

    private final BufferedImage sampleAction = ImageLoader.SAMPLES.getSampleAction();
    private final BufferedImage sampleActivePlayer = ImageLoader.SAMPLES.getSampleActivePlayer();
    private final BufferedImage[] samplesDealer = ImageLoader.SAMPLES.getSamplesDealer();
    private final BufferedImage[] samplesSeat = ImageLoader.SAMPLES.getSamplesSeat();
    private final HashMap<Card, BufferedImage> samplesCardMap = ImageLoader.SAMPLES.getSamplesCardMap();

    public boolean isAction(BufferedImage image) {

        return imageCompare(sampleAction, image);
    }

    public Card parseCard(BufferedImage image) {

        for (Map.Entry<Card, BufferedImage> map : samplesCardMap.entrySet()){
            if (imageCompare(map.getValue(), image)) {
                return map.getKey();
            }
        }

        return null;
    }

    public boolean isDealer(BufferedImage image) {

        for (BufferedImage sample : samplesDealer) {
            if (imageCompare(sample, image)) return true;
        }

        return false;
    }

    public boolean isActivePlayer(BufferedImage image) {

        return imageCompare(sampleActivePlayer, image);
    }

    public boolean isEmptySeat(BufferedImage image) {

        for (BufferedImage sample : samplesSeat) {
            if (imageCompare(sample, image)) return false;
        }

        return true;
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

}
