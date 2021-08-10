package com.epam.rd.java.basic.practice7.pojo;

public class AveLenFlower {

    private final int value;
    private static final String MEASURE = "cm";

    public AveLenFlower(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getMeasure() {
        return MEASURE;
    }
}
