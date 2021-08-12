package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.pojo.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static com.epam.rd.java.basic.practice7.XmlConstants.*;

/**
 * parses xml file using Sax method,
 * creates Flowers container object,
 * saves it to xml file
 */
public class SaxXmlProcessor extends DefaultHandler {

    protected final Flowers flowers = new Flowers();
    protected final String fileName;
    protected final String xsd;

    protected Flower flower;
    protected VisualParameters visualParameters;
    protected GrowingTips growingTips;
    protected Lighting lighting;
    protected StringBuilder text;

    public Flowers getObject() {
        return flowers;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        text = new StringBuilder().append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        switch (qName) {
            case FLOWERS:
                break;
            case FLOWER:
                flower = new Flower();
                break;
            case FLOWER_VISUAL:
                visualParameters = new VisualParameters();
                break;
            case FLOWER_TIPS:
                growingTips = new GrowingTips();
                break;
            case LIGHTING:
                lighting = new Lighting(Lighting.LightRequiring.findValue(
                        attributes.getValue(LIGHT_REQUIRING)));
                break;
            default:
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        processEndTag(qName);
    }

    protected void processEndTag(String qName) {
        switch (qName) {
            case FLOWER:
                flowers.add(flower);
                break;
            case FLOWER_NAME:
                flower.setName(text.toString());
                break;
            case FLOWER_SOIL:
                flower.setSoil(Soil.findValue(text.toString()));
                break;
            case FLOWER_ORIGIN:
                flower.setOrigin(text.toString());
                break;
            case FLOWER_AVE_LEN:
                visualParameters.setAveLenFlower(
                        new AveLenFlower(Integer.parseInt(text.toString())));
                break;
            case FLOWER_STEM_COLOUR:
                visualParameters.setStemColour(text.toString());
                break;
            case FLOWER_LEAF_COLOUR:
                visualParameters.setLeafColour(text.toString());
                break;
            case FLOWER_VISUAL:
                flower.setVisualParameters(visualParameters);
                break;
            case TEMPRETURE:
                growingTips.setTempreture(new Tempreture(Integer.parseInt(text.toString())));
                break;
            case WATERING:
                growingTips.setWatering(new Watering(Integer.parseInt(text.toString())));
                break;
            case LIGHTING:
                growingTips.setLighting(lighting);
                break;
            case FLOWER_MULTIPLYING:
                flower.setMultiplying(Multiplying.findValue(text.toString()));
                break;
            case FLOWER_TIPS:
                flower.setGrowingTips(growingTips);
                break;
            default:
                break;
        }
    }

    public SaxXmlProcessor(String fileName, String xsd) {
        this.fileName = fileName;
        this.xsd = xsd;
    }

    /**
     * parses xml file creating flowers container object
     */
    public void parseFile() {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        try {
            spf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            SAXParser parser = spf.newSAXParser();
            parser.parse(new File(fileName), this);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
    }

    /**
     * tests parsing, sorting and saving to file
     *
     * @param args - command line parameters
     *             args[0] - xml file name
     *             args[1] - xsd file name
     */
    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            throw new IllegalArgumentException("File name expected!");
        }
        String xsd = (args.length > 1) ? args[1] : "";
        SaxXmlProcessor p = new SaxXmlProcessor(args[0], xsd);
        if (!Util.isXmlIsValid(p.fileName, p.xsd)) {
            return;
        }
        p.parseFile();
        if (p.flowers.getFlowers().isEmpty()) {
            return;
        }
        p.flowers.sort(1);
        Util.saveFile("output.sax.xml", p.flowers);
    }
}
