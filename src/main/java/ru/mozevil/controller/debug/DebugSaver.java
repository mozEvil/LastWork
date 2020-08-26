package ru.mozevil.controller.debug;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DebugSaver {

    private static final Logger log = Logger.getLogger(DebugSaver.class);
    private final String path;

    public DebugSaver(String path) {
        this.path = path;
    }

    public void save(BufferedImage image) {
        if (image == null) return;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();

        String name = dtf.format(now);
        String out = path + name + ".png";

        try {
            ImageIO.write(image, "png", new File(out));
        } catch (IOException e) {
            log.log(Level.ERROR," ERROR DEBUG SAVER", e);
        }
    }
}