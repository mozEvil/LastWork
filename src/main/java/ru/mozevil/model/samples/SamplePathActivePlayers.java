package ru.mozevil.model.samples;

public enum SamplePathActivePlayers {

    SAMPLE("src\\main\\resources\\samples\\actives\\active.png");

    private final String filePath;

    SamplePathActivePlayers(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
