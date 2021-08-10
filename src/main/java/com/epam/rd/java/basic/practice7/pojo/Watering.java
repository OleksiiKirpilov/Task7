package com.epam.rd.java.basic.practice7.pojo;

public class Watering {

    private int value;
    private static final String MEASURE = "mlPerWeek";

    public Watering(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static String getMEASURE() {
        return MEASURE;
    }
}
