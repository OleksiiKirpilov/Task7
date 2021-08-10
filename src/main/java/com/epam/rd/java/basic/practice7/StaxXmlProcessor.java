package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.pojo.Flower;
import com.epam.rd.java.basic.practice7.pojo.GrowingTips;
import com.epam.rd.java.basic.practice7.pojo.Lighting;
import com.epam.rd.java.basic.practice7.pojo.VisualParameters;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import java.util.Collections;
import java.util.logging.Logger;

import static com.epam.rd.java.basic.practice7.XmlConstants.*;

public class StaxXmlProcessor extends SaxXmlProcessor {

    @Override
    public void parseFile() {
        XMLInputFactory xif =XMLInputFactory.newInstance();
        xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
        try {
            XMLEventReader r = xif.createXMLEventReader(new StreamSource(fileName));
            while (r.hasNext()) {
                XMLEvent e = r.nextEvent();
                if (e.isStartElement()) {
                    processStartElement(e);
                } else if (e.isEndElement()) {
                    processEndElement(e);
                } else if (e.isCharacters()) {
                    processCharacters(e);
                }
            }
        } catch (XMLStreamException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
    }

    private void processCharacters(XMLEvent e) {
        Characters s = e.asCharacters();
        text = new StringBuilder().append(s);
    }

    private void processEndElement(XMLEvent e) {
        EndElement s = e.asEndElement();
        String tag = s.getName().getLocalPart();
        processEndTag(tag);
    }

    private void processStartElement(XMLEvent e) {
        StartElement s = e.asStartElement();
        String tag = s.getName().getLocalPart();
        switch (tag) {
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
                String val = s.getAttributeByName(new QName(LIGHT_REQUIRING)).getValue();
                lighting = new Lighting(Lighting.LightRequiring.findValue(val));
                break;
            default:
        }

    }

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            throw new IllegalArgumentException("File name expected!");
        }
        String xsd = (args.length > 1) ? args[1] : "";
        StaxXmlProcessor p = new StaxXmlProcessor(args[0], xsd);
        if (!Util.isXmlIsValid(p.fileName, p.xsd)) {
            return;
        }
        p.parseFile();
        p.flowers.sort(2);
        Util.saveFile("output.stax.xml", p.flowers);
    }

    public StaxXmlProcessor(String fileName, String xsd) {
        super(fileName, xsd);
    }

}
