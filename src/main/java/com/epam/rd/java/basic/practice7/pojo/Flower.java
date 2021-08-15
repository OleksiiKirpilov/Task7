package com.epam.rd.java.basic.practice7.pojo;

import java.util.Objects;

/**
 * class for storing flower data
 */
public class Flower implements Comparable<Flower> {

    private String name;
    private Soil soil;
    private String origin;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private Multiplying multiplying;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters visualParameters) {
        this.visualParameters = visualParameters;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }

    @Override
    public int compareTo(Flower o) {
        if (this.equals(o)) {
            return 0;
        }
        return name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Flower)) {
            return false;
        }
        Flower flower = (Flower) o;
        return Objects.equals(name, flower.name)
                && soil.toString().equals(flower.soil.toString())
                && Objects.equals(origin, flower.origin)
                && visualParameters.getAveLenFlower().getValue() == flower.visualParameters.getAveLenFlower().getValue()
                && visualParameters.getLeafColour().equals(flower.visualParameters.getLeafColour())
                && visualParameters.getStemColour().equals(flower.visualParameters.getStemColour())
                && growingTips.getLighting().getLightRequiring().equals(flower.growingTips.getLighting().getLightRequiring())
                && growingTips.getTempreture().getValue() == flower.growingTips.getTempreture().getValue()
                && growingTips.getWatering().getValue() == flower.growingTips.getWatering().getValue()
                && multiplying.equals(flower.multiplying);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, soil, origin, visualParameters, growingTips, multiplying);
    }
}
