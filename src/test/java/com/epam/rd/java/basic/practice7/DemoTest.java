package com.epam.rd.java.basic.practice7;

import org.junit.Assert;
import org.junit.Test;

public class DemoTest {

    @Test()
    public void mainShouldNotThrowException() {
        Demo.main(new String[]{"input.xml"});
    }
}
