package ru.mozevil.controller.debug;

import net.sourceforge.tess4j.util.ImageHelper;
import ru.mozevil.controller.parser.OCRParser;
import ru.mozevil.controller.parser.PokerParser;
import ru.mozevil.controller.parser.TableParser;
import ru.mozevil.controller.parser.cutter.Cutter;
import ru.mozevil.controller.parser.cutter.TableCutter1024;
import ru.mozevil.model.Environment;
import ru.mozevil.view.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DebugParser {

    public static void main(String[] args) throws IOException {

        String path = "C:\\git_repo\\SomePro\\src\\main\\resources\\debug\\";

        String name = "14";

        BufferedImage imgTable = ImageIO.read(new File(path + name + ".png"));

        PokerParser parser = new TableParser();
        parser.setImageTable(imgTable);

        Environment env = parser.parseTable();

        new View().update(env);

    }
}
