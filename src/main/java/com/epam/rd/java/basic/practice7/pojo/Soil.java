package com.epam.rd.java.basic.practice7.pojo;

import static com.epam.rd.java.basic.practice7.XmlConstants.*;

/**
 * class for storing soil type
 */
public enum Soil {

    SOIL1("подзолистая"),
    SOIL2("грунтовая"),
    SOIL3("дерново-подзолистая");

    private final String text;

    Soil(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static Soil findValue(String s) {
        for (Soil v : values()) {
            if (v.toString().equals(s)) {
                return v;
            }
        }
        throw new IllegalArgumentException(ERR_ILLEGAL_ENUM);
    }
}
