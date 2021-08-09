package com.epam.rd.java.basic.practice7;

public class Tempreture {

    private int value;
    private static final String MEASURE = "celcius";

    public Tempreture(int value) {
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