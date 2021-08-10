package com.epam.rd.java.basic.practice7;

import org.junit.Test;

public class MainTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIAEIfNotFilenamePassed() {
        Main.main(new String[0]);
    }

}
