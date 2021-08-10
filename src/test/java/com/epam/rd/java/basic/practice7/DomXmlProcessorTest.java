package com.epam.rd.java.basic.practice7;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class DomXmlProcessorTest {

    private static final String FILE = "input.xml";

    @Test()
    public void mainShouldCreateFile() {
        DomXmlProcessor.main(new String[]{"input.xml"});
        boolean b = false;
        try {
            b = Files.deleteIfExists(Paths.get("output.dom.xml"));
        } catch (IOException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
        Assert.assertTrue(b);
    }

    @Test
    public void shouldCreateFlowersObjectWithSizeGreaterThanZero() {
        DomXmlProcessor p = new DomXmlProcessor(FILE, "");
        p.parseFile();
        Assert.assertTrue(p.getObject().getFlowers().size() > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIAEIfNotFilenamePassed() {
        DomXmlProcessor.main(new String[0]);
    }

}
