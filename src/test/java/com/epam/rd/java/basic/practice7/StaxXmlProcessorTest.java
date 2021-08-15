package com.epam.rd.java.basic.practice7;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class StaxXmlProcessorTest {

    private static final String FILE = "input.xml";

    @Test()
    public void mainShouldCreateFile() {
        StaxXmlProcessor.main(new String[]{FILE});
        boolean b = false;
        try {
            b = Files.deleteIfExists(Paths.get("output.stax.xml"));
        } catch (IOException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
        Assert.assertTrue(b);
    }

    @Test
    public void shouldCreateFlowersObjectWithSizeGreaterThanZero() {
        StaxXmlProcessor p = new StaxXmlProcessor(FILE, "");
        p.parseFile();
        Assert.assertTrue(p.getObject().getFlowers().size() > 0);
    }

    @Test
    public void shouldCreateEmptyFlowersObjectIfWrongFile() {
        StaxXmlProcessor p = new StaxXmlProcessor("input.txt", "");
        p.parseFile();
        Assert.assertTrue(p.getObject().getFlowers().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIAEIfNotFilenamePassed() {
        StaxXmlProcessor.main(new String[0]);
    }

}


