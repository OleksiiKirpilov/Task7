package com.epam.rd.java.basic.practice7;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class DemoTest {

    @Test()
    public void mainShouldCreateFiles() {
        Demo.main(new String[]{"input.xml"});
        boolean b = false;
        try {
            b = Files.deleteIfExists(Paths.get("output.dom.xml"));
            b &= Files.deleteIfExists(Paths.get("output.sax.xml"));
            b &= Files.deleteIfExists(Paths.get("output.stax.xml"));
        } catch (IOException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
        Assert.assertTrue(b);
    }

}
