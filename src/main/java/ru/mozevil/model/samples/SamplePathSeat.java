package ru.mozevil.model.samples;

public enum SamplePathSeat {

    //1366
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
    SAMPLE_16("src\\main\\resources\\samples\\seats\\s16.png"),

    //1024
    SAMPLE_17("src\\main\\resources\\samples\\seats\\s17.png"),
    SAMPLE_18("src\\main\\resources\\samples\\seats\\s18.png"),
    SAMPLE_19("src\\main\\resources\\samples\\seats\\s19.png"),
    SAMPLE_20("src\\main\\resources\\samples\\seats\\s20.png"),
    SAMPLE_21("src\\main\\resources\\samples\\seats\\s21.png"),
    SAMPLE_22("src\\main\\resources\\samples\\seats\\s22.png"),
    SAMPLE_23("src\\main\\resources\\samples\\seats\\s23.png"),
    SAMPLE_24("src\\main\\resources\\samples\\seats\\s24.png"),
    SAMPLE_25("src\\main\\resources\\samples\\seats\\s25.png"),
    SAMPLE_26("src\\main\\resources\\samples\\seats\\s26.png"),
    SAMPLE_27("src\\main\\resources\\samples\\seats\\s27.png"),
    SAMPLE_28("src\\main\\resources\\samples\\seats\\s28.png"),
    SAMPLE_29("src\\main\\resources\\samples\\seats\\s29.png"),
    SAMPLE_30("src\\main\\resources\\samples\\seats\\s30.png"),
    SAMPLE_31("src\\main\\resources\\samples\\seats\\s31.png"),
    SAMPLE_32("src\\main\\resources\\samples\\seats\\s32.png");

    private final String filePath;

    SamplePathSeat(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
