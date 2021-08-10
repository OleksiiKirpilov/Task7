package com.epam.rd.java.basic.practice7;

import java.util.ArrayList;
import java.util.List;

public class Flowers {

    private List<Flower> list;

    public void add(Flower f) {
        list.add(f);
    }

    public Flowers() {
        list = new ArrayList<>();
    }

    public List<Flower> getFlowers() {
        return list;
    }

    public void setFlowers(List<Flower> flowers) {
        list = flowers;
    }
}
