package ru.mozevil.model.samples;

public enum SamplePathDealer {

    SAMPLE_0("src\\main\\resources\\samples\\dealer\\d0.png"),
    SAMPLE_1("src\\main\\resources\\samples\\dealer\\d1.png"),
    SAMPLE_2("src\\main\\resources\\samples\\dealer\\d2.png"),
    SAMPLE_3("src\\main\\resources\\samples\\dealer\\d3.png"),
    SAMPLE_4("src\\main\\resources\\samples\\dealer\\d4.png"),
    SAMPLE_5("src\\main\\resources\\samples\\dealer\\d5.png"),
    SAMPLE_6("src\\main\\resources\\samples\\dealer\\d6.png"),
    SAMPLE_7("src\\main\\resources\\samples\\dealer\\d7.png"),
    SAMPLE_8("src\\main\\resources\\samples\\dealer\\d8.png"),
    SAMPLE_9("src\\main\\resources\\samples\\dealer\\d9.png");

    private final String filePath;

    SamplePathDealer(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
