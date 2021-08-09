package com.epam.rd.java.basic.practice7;

public class VisualParameters {

    private String stemColour;
    private String leafColour;
    private AveLenFlower aveLenFlower;

    public String getStemColour() {
        return stemColour;
    }

    public void setStemColour(String stemColour) {
        this.stemColour = stemColour;
    }

    public String getLeafColour() {
        return leafColour;
    }

    public void setLeafColour(String leafColour) {
        this.leafColour = leafColour;
    }

    public AveLenFlower getAveLenFlower() {
        return aveLenFlower;
    }

    public void setAveLenFlower(AveLenFlower aveLenFlower) {
        this.aveLenFlower = aveLenFlower;
    }

    public static class AveLenFlower {
        private int value;
        private static final String MEASURE = "cm";

        public AveLenFlower(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getMeasure() {
            return MEASURE;
        }
    }

}