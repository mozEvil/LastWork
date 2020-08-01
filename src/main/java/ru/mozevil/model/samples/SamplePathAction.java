package ru.mozevil.model.samples;

public enum SamplePathAction {

    SAMPLE("src\\main\\resources\\samples\\action\\action.png");

    private final String filePath;

    SamplePathAction(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
