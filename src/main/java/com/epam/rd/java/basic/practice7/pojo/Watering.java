package com.epam.rd.java.basic.practice7.pojo;

/**
 * class for storing watering info
 */
public class Watering {

    private static final String MEASURE = "mlPerWeek";

    private final int value;

    public Watering(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getMEASURE() {
        return MEASURE;
    }
}
