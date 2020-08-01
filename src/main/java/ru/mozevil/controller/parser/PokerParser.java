package ru.mozevil.controller.parser;

import ru.mozevil.model.Table;

import java.awt.image.BufferedImage;

public interface PokerParser {
    void setImageTable(BufferedImage imageTable);
    boolean isAction();
    Table parseTable();
}
