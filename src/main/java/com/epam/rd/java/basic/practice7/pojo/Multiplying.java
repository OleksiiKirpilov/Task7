package com.epam.rd.java.basic.practice7.pojo;

import static com.epam.rd.java.basic.practice7.XmlConstants.ERR_ILLEGAL_ENUM;

public enum Multiplying {

    MULT1("листья"),
    MULT2("черенки"),
    MULT3("семена");

    private final String text;

    Multiplying(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static Multiplying findValue(String s) {
        for (Multiplying v : values()) {
            if (v.toString().equals(s)) {
                return v;
            }
        }
        throw new IllegalArgumentException(ERR_ILLEGAL_ENUM);
    }

}
