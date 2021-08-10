package com.epam.rd.java.basic.practice7.pojo;

import java.util.Objects;

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
                && soil == flower.soil
                && Objects.equals(origin, flower.origin)
                && Objects.equals(visualParameters, flower.visualParameters)
                && Objects.equals(growingTips, flower.growingTips)
                && multiplying == flower.multiplying;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, soil, origin, visualParameters, growingTips, multiplying);
    }
}
