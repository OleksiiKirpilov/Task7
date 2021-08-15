package com.epam.rd.java.basic.practice7;

import org.junit.Test;

public class FlowersTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfWrongSortMethod() {
        DomXmlProcessor d = new DomXmlProcessor("input.xml", "");
        d.parseFile();
        d.getObject().sort(5);
    }


}
