package com.epam.rd.java.basic.practice7;

import java.util.Collections;

public class StaxXmlProcessor {

    private final Flowers flowers = new Flowers();
    private final String fileName;

    public void parseFile() {
        // not implemented yet
    }

    public void saveFile(String fileName) {
        // not implemented yet
    }

    public static void main(String[] args) {
        StaxXmlProcessor p = new StaxXmlProcessor(args[0]);
        p.parseFile();
        Collections.sort(p.flowers.getFlowers());
        p.saveFile("output.stax.xml");
    }

    public StaxXmlProcessor(String fileName) {
        this.fileName = fileName;
    }

}
