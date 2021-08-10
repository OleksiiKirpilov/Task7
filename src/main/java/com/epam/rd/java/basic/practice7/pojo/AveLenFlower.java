package com.epam.rd.java.basic.practice7.pojo;

/**
 * class for storing AveLenFlower info
 */
public class AveLenFlower {

    private static final String MEASURE = "cm";

    private final int value;

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
