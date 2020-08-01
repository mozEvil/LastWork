package ru.mozevil.controller.strategy;

import ru.mozevil.controller.parser.TableParser;
import ru.mozevil.model.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestStrategy {

    public static void main(String[] args) throws IOException {

        int num = 49;
        BufferedImage imageTable = ImageIO.read(new File("src\\main\\resources\\samples\\tables\\a\\"+ num +".png"));
        TableParser parser = new TableParser();
        parser.setImageTable(imageTable);
        Table table = parser.parseTable();
        Environment env = new Environment(table);
        PokerStrategy strategy = new SnG_45_simple();
        Decision decision = strategy.makeDecision(env);

        System.out.println(decision);
        System.out.println(env.getHeroPosition());
        System.out.println(env.getHeroHand());
        System.out.println(env.isOpenRaise());

    }
}
