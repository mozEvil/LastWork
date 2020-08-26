package ru.mozevil.controller.parser.cutter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ru.mozevil.model.map.TableMap;
import java.awt.image.BufferedImage;

public abstract class Cutter {

    private static final Logger log = Logger.getLogger(Cutter.class.getName());

    private BufferedImage imageTable;

    public void setImageTable(BufferedImage imageTable) {
        this.imageTable = imageTable;
    }

    protected BufferedImage findBetImg(BufferedImage image) {
        BufferedImage result = null;
        int[] betFieldRBG = new int[] {
                -16688868, -16688867, -16688355, -16688612, -16688611, -12940717, -16689124, -12940973,
                -16689380, -16689636, -12941230, -16691687, -16689893, -16690406, -16690406, -16690149,
                -12941742, -16691943, -16690662, -16693481, -16691431, -16691175, -16692969, -16691174
        };
        boolean stopFind = false;
        // find from left to right
        for (int i = 0; i < image.getWidth(); i++) {
            for (int rgb : betFieldRBG) {
                if (rgb == image.getRGB(i, 8)) {
                    result = image.getSubimage(i, 0, image.getWidth() - i, image.getHeight());
                    stopFind = true;
                    break;
                }
            }
            if (stopFind) break;
        }
        if (result != null) {
            // find from right to left
            for (int i = result.getWidth() - 1; i >= 0; i--) {
                for (int rgb : betFieldRBG) {
                    if (rgb == result.getRGB(i, 8)) {
                        return result.getSubimage(0, 0, i + 1, image.getHeight());
                    }
                }
            }
        }
        return result;
//        return image;
    }

    protected BufferedImage cutTable(TableMap tableMap) {
        BufferedImage img = null;
        try {
            img = imageTable.getSubimage(tableMap.getX(), tableMap.getY(), tableMap.getW(), tableMap.getH());
        } catch (Exception e) {
            log.log(Level.ERROR, "ERROR CutTable", e);
        }
        return img;
    }

    public abstract BufferedImage getHandCardImg(int i);

    public abstract BufferedImage getTableCardImg(int i);

    public abstract BufferedImage getSeatImg(int i);

    public abstract BufferedImage getActivePlayerImg(int i);

    public abstract BufferedImage getBetSizeImg(int i);

    public abstract BufferedImage getStackSizeImg(int i);

    public abstract BufferedImage getDealerImg(int i);

    public abstract BufferedImage getActionBtnImg(int i);

    public abstract BufferedImage getPotImg();

    public abstract BufferedImage getLvlImg();
}
