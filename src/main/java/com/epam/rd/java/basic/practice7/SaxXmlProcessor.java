package com.epam.rd.java.basic.practice7;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;

import static com.epam.rd.java.basic.practice7.XmlConstants.*;

public class SaxXmlProcessor extends DefaultHandler {

    private final Flowers flowers = new Flowers();
    private final String fileName;

    private Flower flower;
    private VisualParameters visualParameters;
    private GrowingTips growingTips;
    private Lighting lighting;
    private StringBuilder text;


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new StringBuilder().append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
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
                text = null;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
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

    public SaxXmlProcessor(String fileName) {
        this.fileName = fileName;
    }

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

    public static void main(String[] args) {
        SaxXmlProcessor p = new SaxXmlProcessor(args[0]);
        p.parseFile();
        Collections.sort(p.flowers.getFlowers());
        Util.saveFile("output.sax.xml", p.flowers);
    }
}
