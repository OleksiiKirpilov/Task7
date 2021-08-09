package com.epam.rd.java.basic.practice7;

import static com.epam.rd.java.basic.practice7.XmlConstants.ERR_ILLEGAL_ENUM;

public class Lighting {

    public LightRequiring lightRequiring;

    public enum LightRequiring {
        YES("yes"),
        NO("no");

        private final String text;

        LightRequiring(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public static LightRequiring findValue(String s) {
            for (LightRequiring v : values()) {
                if (v.toString().equals(s)) {
                    return v;
                }
            }
            throw new IllegalArgumentException(ERR_ILLEGAL_ENUM + " " + s);
        }

    }

}