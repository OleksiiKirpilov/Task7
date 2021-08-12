package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.pojo.Multiplying;
import com.epam.rd.java.basic.practice7.pojo.Soil;
import org.junit.Test;

public class MultiplyingTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfWrongValue() {
        Multiplying.findValue("wrong value");
    }

}
