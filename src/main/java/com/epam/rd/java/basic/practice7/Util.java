package com.epam.rd.java.basic.practice7;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import static com.epam.rd.java.basic.practice7.XmlConstants.*;

public class Util {

    private Util() {}

    public static void saveFile(String fileName, Flowers flowers) {
        XMLOutputFactory xof = XMLOutputFactory.newInstance();
        try (FileWriter fw = new FileWriter(fileName)) {
            XMLStreamWriter w = xof.createXMLStreamWriter(fw);
            w.writeStartDocument();
            w.writeStartElement(FLOWERS);
            w.writeAttribute("xmlns:tns", "http://www.example.org/input");
            w.writeAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            w.writeAttribute("xsi:schemaLocation", "http://www.example.org/input input.xsd");
            for (Flower f : flowers.getFlowers()) {
                w.writeStartElement(FLOWER);
                appendElement(w, FLOWER_NAME, f.getName());
                appendElement(w, FLOWER_SOIL, f.getSoil().toString());
                appendElement(w, FLOWER_ORIGIN, f.getOrigin());
                // append visualParameters
                w.writeStartElement(FLOWER_VISUAL);
                appendElement(w, FLOWER_STEM_COLOUR, f.getVisualParameters().getStemColour());
                appendElement(w, FLOWER_LEAF_COLOUR, f.getVisualParameters().getLeafColour());
                appendElementWithAttribute(w, FLOWER_AVE_LEN,
                        String.valueOf(f.getVisualParameters().getAveLenFlower().getValue()),
                        MEASURE, AveLenFlower.getMeasure());
                w.writeEndElement();
                // append growingTips
                w.writeStartElement(FLOWER_TIPS);
                appendElementWithAttribute(w, TEMPRETURE,
                        String.valueOf(f.getGrowingTips().getTempreture().getValue()),
                        MEASURE, Tempreture.getMEASURE());
                appendElementWithAttribute(w, LIGHTING, "", LIGHT_REQUIRING,
                        f.getGrowingTips().getLighting().getLightRequiring().toString());
                appendElementWithAttribute(w, WATERING,
                        String.valueOf(f.getGrowingTips().getWatering().getValue()),
                        MEASURE, Watering.getMEASURE());
                w.writeEndElement();
                // append multiplying
                appendElement(w, FLOWER_MULTIPLYING, f.getMultiplying().toString());
                w.writeEndElement();
            }
            // close root tag
            w.writeEndElement();
        } catch (XMLStreamException | IOException e) {
            Logger.getGlobal().severe(e.getMessage());
        }

    }

    private static void appendElement(XMLStreamWriter w, String name, String text)
            throws XMLStreamException {
        w.writeStartElement(name);
        if (!text.isEmpty()) {
            w.writeCharacters(text);
        }
        w.writeEndElement();
    }

    private static void appendElementWithAttribute(
            XMLStreamWriter w, String name, String text,
            String attrName, String attrValue) throws XMLStreamException {
        w.writeStartElement(name);
        w.writeAttribute(attrName, attrValue);
        if (!text.isEmpty()) {
            w.writeCharacters(text);
        }
        w.writeEndElement();
    }

}
