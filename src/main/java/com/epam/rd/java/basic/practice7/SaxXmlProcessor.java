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

    protected final Flowers flowers = new Flowers();
    protected final String fileName;

    protected Flower flower;
    protected VisualParameters visualParameters;
    protected GrowingTips growingTips;
    protected Lighting lighting;
    protected StringBuilder text;


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
        Util.processEndTag(qName, this);
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
