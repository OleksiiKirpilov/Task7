package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.pojo.Lighting;
import org.junit.Test;

public class LightingTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfWrongValue() {
        Lighting.LightRequiring.findValue("wrong value");
    }

}
