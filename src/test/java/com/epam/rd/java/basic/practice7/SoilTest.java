package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.pojo.Soil;
import org.junit.Test;

public class SoilTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfWrongValue() {
        Soil.findValue("wrong value");
    }
}
