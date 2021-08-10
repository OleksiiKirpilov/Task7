package com.epam.rd.java.basic.practice7.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Container class for storing xml data.
 * Contains list of Flower objects.
 */
public class Flowers {

    private final List<Flower> list;

    /**
     * Adds Flower object to container
      * @param f
     */
    public void add(Flower f) {
        list.add(f);
    }

    public Flowers() {
        list = new ArrayList<>();
    }

    public List<Flower> getFlowers() {
        return list;
    }

    /**
     * Implements different sorting methods.
     * @param type  type = 0 - uses natural ordering (by name)
     *              type = 1 - by average flowers length
     *              type = 2 - by flower origin
     */
    public void sort(int type) {
        switch (type) {
            case 0:
                Collections.sort(list);
                break;
            case 1:
                list.sort(Flowers::compareFlowersByLength);
                break;
            case 2:
                list.sort(Flowers::compareFlowersByOrigin);
                break;
            default:
                throw new IllegalArgumentException("Wrong sort type!");
        }
    }

    private static int compareFlowersByLength(Flower f1, Flower f2) {
        Integer l1 = f1.getVisualParameters().getAveLenFlower().getValue();
        Integer l2 = f2.getVisualParameters().getAveLenFlower().getValue();
        return l1.compareTo(l2);
    }

    private static int compareFlowersByOrigin(Flower f1, Flower f2) {
        return f1.getOrigin().compareTo(f2.getOrigin());
    }

}
