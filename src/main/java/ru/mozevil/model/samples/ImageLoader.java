package ru.mozevil.model.samples;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.mozevil.model.Card;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageLoader {

    private static final Logger log = Logger.getLogger(ImageLoader.class.getName());

    public static final ImageLoader SAMPLES = new ImageLoader();

    private BufferedImage sampleAction;
    private BufferedImage sampleActivePlayer;
    private HashMap<Card, BufferedImage> samplesCard;
    private BufferedImage[] samplesDealer;
    private BufferedImage[] samplesSeat;

    private ImageLoader() {
        loadActionSamples();
        loadCardSamples();
        loadDealerSamples();
        loadActivePlayerSamples();
        loadSeatSamples();
    }

    private void loadActionSamples() {
        try {
            sampleAction = ImageIO.read(new File(SamplePathAction.SAMPLE.getFilePath()));
        } catch (IOException e) {
            log.log(Level.ERROR,"ERROR LOAD ACTION SAMPLES", e);
        }
    }

    private void loadCardSamples() {
        try {
            samplesCard = new HashMap<>();
            for (SamplePathCard c : SamplePathCard.values()) {
                BufferedImage image = ImageIO.read(new File(c.getFilePath()));
                samplesCard.put(c.getCard(), image);
            }
        } catch (IOException e) {
            log.log(Level.ERROR,"ERROR LOAD CARD SAMPLES", e);
        }
    }

    private void loadDealerSamples() {
        try {
            samplesDealer = new BufferedImage[SamplePathDealer.values().length];
            for (int i = 0; i < samplesDealer.length; i++) {
                samplesDealer[i] = ImageIO.read(new File(SamplePathDealer.values()[i].getFilePath()));
            }
        } catch (IOException e) {
            log.log(Level.ERROR,"ERROR LOAD DEALER SAMPLES", e);
        }
    }

    private void loadActivePlayerSamples() {
        try {
            sampleActivePlayer = ImageIO.read(new File(SamplePathActivePlayers.SAMPLE.getFilePath()));

        } catch (IOException e) {
            log.log(Level.ERROR,"ERROR LOAD ACTIVE SAMPLES", e);
        }
    }

    private void loadSeatSamples() {
        try {
            samplesSeat = new BufferedImage[SamplePathSeat.values().length];
            for (int i = 0; i < samplesSeat.length; i++) {
                samplesSeat[i] = ImageIO.read(new File(SamplePathSeat.values()[i].getFilePath()));
            }
        } catch (IOException e) {
            log.log(Level.ERROR,"ERROR LOAD SEAT SAMPLES", e);
        }
    }

    public BufferedImage Action() {
        return sampleAction;
    }

    public BufferedImage[] Dealer() {
        return samplesDealer;
    }

    public BufferedImage ActivePlayer() {
        return sampleActivePlayer;
    }

    public BufferedImage[] Seat() {
        return samplesSeat;
    }

    public HashMap<Card, BufferedImage> Card() {
        return samplesCard;
    }

}
