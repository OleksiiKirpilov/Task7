package com.epam.rd.java.basic.practice7;

public final class Main {

    /**
     *  runs all 3 parsers using the same command line
     *  parameters.
     * @param args
     * args[0] - xml file name
     * args[1] - xsd file name
     */
    public static void main(final String[] args) {
        DomXmlProcessor.main(args);
        SaxXmlProcessor.main(args);
        StaxXmlProcessor.main(args);
    }

}
