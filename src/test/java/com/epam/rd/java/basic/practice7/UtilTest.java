package com.epam.rd.java.basic.practice7;

import org.junit.Assert;
import org.junit.Test;

public class UtilTest {

    @Test()
    public void mainReturnTrueIfValid() {
        Assert.assertTrue(Util.isXmlIsValid("input.xml", "input.xsd"));
    }

    @Test()
    public void mainReturnFalseIfInvalid() {
        Assert.assertFalse(Util.isXmlIsValid("invalidXML.xml", "input.xsd"));
    }

    @Test()
    public void mainReturnFalseIfXsdNotFound() {
        Assert.assertFalse(Util.isXmlIsValid("invalidXML.xml", "input.xxsd"));
    }

    @Test()
    public void mainReturnFalseIfXmlNotFound() {
        Assert.assertFalse(Util.isXmlIsValid("invalidXML-.xml", "input.xsd"));
    }

}
