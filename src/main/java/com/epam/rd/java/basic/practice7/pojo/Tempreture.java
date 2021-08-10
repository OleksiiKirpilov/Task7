package com.epam.rd.java.basic.practice7.pojo;

/**
 * class for storing temperature
 */
public class Tempreture {

    private static final String MEASURE = "celcius";

    private final int value;

    public Tempreture(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static String getMEASURE() {
        return MEASURE;
    }
}