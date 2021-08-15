package com.epam.rd.java.basic.practice7;

import org.junit.Assert;
import org.junit.Test;

public class UtilTest {

    private static final String XML = "input.xml";
    private static final String XSD = "input.xsd";
    private static final String INVALID_XML = "invalidXML.xml";

    @Test()
    public void mainReturnTrueIfValid() {
        Assert.assertTrue(Util.isXmlIsValid(XML, XSD));
    }

    @Test()
    public void mainReturnFalseIfInvalid() {
        Assert.assertFalse(Util.isXmlIsValid(INVALID_XML, XSD));
    }

    @Test()
    public void mainReturnFalseIfXsdNotFound() {
        Assert.assertFalse(Util.isXmlIsValid(INVALID_XML, "input.xxsd"));
    }

    @Test()
    public void mainReturnFalseIfXmlNotFound() {
        Assert.assertFalse(Util.isXmlIsValid("invalidXML-.xml", XSD));
    }

}
