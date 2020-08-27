package ru.mozevil.controller.debug;

import ru.mozevil.controller.parser.PokerParser;
import ru.mozevil.controller.parser.TableParser;
import ru.mozevil.model.Environment;
import ru.mozevil.view.PokerView;
import ru.mozevil.view.View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DebugParser {

    public static void main(String[] args) throws IOException {
//        long startTime = System.nanoTime();


        PokerView view = new View();
        PokerParser parser = new TableParser();

        String path = "C:\\git_repo\\SomePro\\src\\main\\resources\\debug\\1\\";

//        for (int i = 1; i <= 196 ; i++) {
            BufferedImage imgTable = ImageIO.read(new File(path + 165 + ".png"));
            parser.setImageTable(imgTable);
            parser.parseTable();
//            System.out.println(i);
            Environment env = parser.parseTable();
            view.update(env);
//        }

//        long stopTime = System.nanoTime();
//        System.out.println(stopTime - startTime);
    }
}
