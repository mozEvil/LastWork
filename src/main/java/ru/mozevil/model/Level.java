package ru.mozevil.model;

import java.util.HashMap;

public class Level {

    private final HashMap<Integer, Double> lvlMap = new HashMap<>();

    public void addLevel(int levelNumber, double anteSize) {

        lvlMap.put(levelNumber, anteSize);
    }

    /** возвращает размер анте в больших блаиндах*/
    public double getAnte(int levelNumber) {

        return lvlMap.get(levelNumber);
    }

}
