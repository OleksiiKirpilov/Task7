package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.pojo.Flower;
import com.epam.rd.java.basic.practice7.pojo.Flowers;
import org.junit.Assert;
import org.junit.Test;

public class FlowerTest {

    private static final String INPUT = "input.xml";

    @Test
    public void shouldReturnTrueIfEqualFlowersAreEqual() {
        DomXmlProcessor p1 = new DomXmlProcessor(INPUT, "");
        SaxXmlProcessor p2 = new SaxXmlProcessor(INPUT, "");
        p1.parseFile();
        p2.parseFile();
        Flower f1 = p1.getObject().getFlowers().get(0);
        Flower f2 = p1.getObject().getFlowers().get(1);
        Flower f3 = p2.getObject().getFlowers().get(0);
        Assert.assertNotEquals(f1, f2);
        Assert.assertEquals(f1, f3);
    }

    @Test
    public void equalsShouldReturnTrueIfSameObjects() {
        DomXmlProcessor p1 = new DomXmlProcessor(INPUT, "");
        p1.parseFile();
        Flower f1 = p1.getObject().getFlowers().get(0);
        Assert.assertEquals(f1, f1);
    }

    @Test
    public void equalsShouldReturnFalseIfObjectsAreDifferentClasses() {
        DomXmlProcessor p1 = new DomXmlProcessor(INPUT, "");
        p1.parseFile();
        Flower f1 = p1.getObject().getFlowers().get(0);
        Assert.assertNotEquals(f1, p1);
    }

    @Test
    public void shouldAddElements() {
        DomXmlProcessor p1 = new DomXmlProcessor(INPUT, "");
        p1.parseFile();
        Flowers c = p1.getObject();
        Flower f1 = c.getFlowers().get(0);
        c.add(f1);
        c.sort(0);
        Assert.assertEquals(3, c.getFlowers().size());
    }
}
