package ru.mozevil.model.samples;

public enum SamplePathSeat {

    SAMPLE_1("src\\main\\resources\\samples\\seats\\s1.png"),
    SAMPLE_2("src\\main\\resources\\samples\\seats\\s2.png"),
    SAMPLE_3("src\\main\\resources\\samples\\seats\\s3.png"),
    SAMPLE_4("src\\main\\resources\\samples\\seats\\s4.png"),
    SAMPLE_5("src\\main\\resources\\samples\\seats\\s5.png"),
    SAMPLE_6("src\\main\\resources\\samples\\seats\\s6.png"),
    SAMPLE_7("src\\main\\resources\\samples\\seats\\s7.png"),
    SAMPLE_8("src\\main\\resources\\samples\\seats\\s8.png"),
    SAMPLE_9("src\\main\\resources\\samples\\seats\\s9.png"),
    SAMPLE_10("src\\main\\resources\\samples\\seats\\s10.png"),
    SAMPLE_11("src\\main\\resources\\samples\\seats\\s11.png"),
    SAMPLE_12("src\\main\\resources\\samples\\seats\\s12.png"),
    SAMPLE_13("src\\main\\resources\\samples\\seats\\s13.png"),
    SAMPLE_14("src\\main\\resources\\samples\\seats\\s14.png"),
    SAMPLE_15("src\\main\\resources\\samples\\seats\\s15.png"),
    SAMPLE_16("src\\main\\resources\\samples\\seats\\s16.png");

    private final String filePath;

    SamplePathSeat(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
