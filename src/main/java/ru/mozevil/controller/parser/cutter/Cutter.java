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
        BufferedImage img = image;
        int[] betFieldRBG = new int[] {
                -16688868, -16688867, -16688355, -16688612, -16688611, -12940717, -16689124, -12940973,
                -16689380, -16689636, -12941230, -16691687, -16689893, -16690406, -16690406, -16690149,
                -12941742, -16691943, -16690662, -16693481, -16691431, -16691175
        };
        boolean stopFind = false;
        for (int i = 0; i < img.getWidth(); i++) {
            int  rbg = img.getRGB(i, 9);
            for (int value : betFieldRBG) {
                if (value == rbg) {
                    img = img.getSubimage(i, 0, img.getWidth() - i, img.getHeight());
                    stopFind = true;
                    break;
                }
            }
            if (stopFind) {
                stopFind = false;
                break;
            }
        }
        for (int i = img.getWidth()-1; i > 0; i--) {
            int  rbg = img.getRGB(i, 9);
            for (int value : betFieldRBG) {
                if (value == rbg) {
                    img = img.getSubimage(0, 0, i+1, img.getHeight());
                    stopFind = true;
                    break;
                }
            }
            if (stopFind) break;
        }
        return img;
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
