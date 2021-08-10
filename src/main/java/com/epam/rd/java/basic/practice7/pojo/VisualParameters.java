package com.epam.rd.java.basic.practice7.pojo;

/**
 * class for storing visual parameters data
 */
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

}