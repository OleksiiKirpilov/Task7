package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.pojo.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;

import static com.epam.rd.java.basic.practice7.XmlConstants.*;

public class DomXmlProcessor {

    private final Flowers flowers = new Flowers();
    private final String fileName;
    private final String xsd;
    private Document xmlDoc;


    public DomXmlProcessor(String fileName, String xsd) {
        this.fileName = fileName;
        this.xsd = xsd;
    }

    public Flowers getObject() {
        return flowers;
    }

    public void parseFile() {
        DocumentBuilderFactory df = DocumentBuilderFactory.newInstance();
        df.setNamespaceAware(false);
        try {
            df.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            xmlDoc = df.newDocumentBuilder().parse(fileName);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
        NodeList flowerNodes = xmlDoc.getElementsByTagName("flower");
        for (int i = 0; i < flowerNodes.getLength(); i++) {
            NodeList f = flowerNodes.item(i).getChildNodes();
            flowers.add(parse(f));
        }
    }

    private Flower parse(NodeList nodeList) {
        Flower flower = new Flower();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            switch (node.getNodeName()) {
                case FLOWER_NAME:
                    flower.setName(node.getTextContent());
                    break;
                case FLOWER_SOIL:
                    flower.setSoil(Soil.findValue(node.getTextContent()));
                    break;
                case FLOWER_ORIGIN:
                    flower.setOrigin(node.getTextContent());
                    break;
                case FLOWER_VISUAL:
                    setVisualParameters(flower, node.getChildNodes());
                    break;
                case FLOWER_TIPS:
                    setGrowingTips(flower, node.getChildNodes());
                    break;
                case FLOWER_MULTIPLYING:
                    flower.setMultiplying(Multiplying.findValue(node.getTextContent()));
                    break;
                case INDENT:
                    break;
                default:
                    Logger.getGlobal().severe(ERR_UNKNOWN_NODE + node.getNodeName());
            }
        }
        return flower;
    }

    private void setVisualParameters(Flower flower, NodeList nodes) {
        VisualParameters vp = new VisualParameters();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            switch (node.getNodeName()) {
                case FLOWER_STEM_COLOUR:
                    vp.setStemColour(node.getTextContent());
                    break;
                case FLOWER_LEAF_COLOUR:
                    vp.setLeafColour(node.getTextContent());
                    break;
                case FLOWER_AVE_LEN:
                    vp.setAveLenFlower(new AveLenFlower(Integer.parseInt(node.getTextContent())));
                    break;
                case INDENT:
                    break;
                default:
                    Logger.getGlobal().severe(ERR_UNKNOWN_NODE + node.getNodeName());
            }
        }
        flower.setVisualParameters(vp);
    }

    private void setGrowingTips(Flower flower, NodeList nodes) {
        GrowingTips gt = new GrowingTips();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            switch (node.getNodeName()) {
                case TEMPRETURE:
                    gt.setTempreture(new Tempreture(Integer.parseInt(node.getTextContent())));
                    break;
                case LIGHTING:
                    Lighting.LightRequiring lightRequiring =
                            Lighting.LightRequiring.findValue(node.getAttributes().
                                    getNamedItem("lightRequiring").getTextContent());
                    gt.setLighting(new Lighting(lightRequiring));
                    break;
                case WATERING:
                    gt.setWatering(new Watering(Integer.parseInt(node.getTextContent())));
                    break;
                case INDENT:
                    break;
                default:
                    Logger.getGlobal().severe(ERR_UNKNOWN_NODE + node.getNodeName());
            }
        }
        flower.setGrowingTips(gt);
    }

    public void saveFile(String fileName) {
        StreamResult out = new StreamResult(new File(fileName));
        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
        Transformer t;
        try {
            t = tf.newTransformer();
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.transform(new DOMSource(createDoc()), out);
        } catch (TransformerException | ParserConfigurationException e) {
            Logger.getGlobal().severe(e.getMessage());
        }
    }

    private void appendChild(Document d, Element parent, String childName, String childText) {
        Element e = d.createElement(childName);
        e.setTextContent(childText);
        parent.appendChild(e);
    }

    private void appendChildWithAttribute(Document d, Element parent, String childName,
                                          String childText, String attrName, String attrText) {
        Element e = d.createElement(childName);
        if (!childText.isEmpty()) {
            e.setTextContent(childText);
        }
        e.setAttribute(attrName, attrText);
        parent.appendChild(e);
    }

    private Node createDoc() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        Element root = doc.createElement(FLOWERS);
        root.setAttribute("xmlns:tns", "http://www.example.org/input");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xsi:schemaLocation", "http://www.example.org/input input.xsd");
        doc.appendChild(root);
        for (Flower f : flowers.getFlowers()) {
            Element flower = doc.createElement(FLOWER);
            // append name, soil, origin
            appendChild(doc, flower, FLOWER_NAME, f.getName());
            appendChild(doc, flower, FLOWER_SOIL, f.getSoil().toString());
            appendChild(doc, flower, FLOWER_ORIGIN, f.getOrigin());
            // append visualParameters
            Element vp = doc.createElement(FLOWER_VISUAL);
            appendChild(doc, vp, FLOWER_STEM_COLOUR, f.getVisualParameters().getStemColour());
            appendChild(doc, vp, FLOWER_LEAF_COLOUR, f.getVisualParameters().getLeafColour());
            appendChildWithAttribute(doc, vp, FLOWER_AVE_LEN,
                    String.valueOf(f.getVisualParameters().getAveLenFlower().getValue()),
                    MEASURE, AveLenFlower.getMeasure());
            flower.appendChild(vp);
            // append growingTips
            Element gt = doc.createElement(FLOWER_TIPS);
            appendChildWithAttribute(doc, gt, TEMPRETURE,
                    String.valueOf(f.getGrowingTips().getTempreture().getValue()),
                    MEASURE, Tempreture.getMEASURE());
            appendChildWithAttribute(doc, gt, LIGHTING, "", LIGHT_REQUIRING,
                    f.getGrowingTips().getLighting().getLightRequiring().toString());
            appendChildWithAttribute(doc, gt, WATERING,
                    String.valueOf(f.getGrowingTips().getWatering().getValue()),
                    MEASURE, Watering.getMEASURE());
            flower.appendChild(gt);
            appendChild(doc, flower, FLOWER_MULTIPLYING, f.getMultiplying().toString());
            root.appendChild(flower);
        }
        return doc;
    }

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            throw new IllegalArgumentException("File name expected!");
        }
        String xsd = (args.length > 1) ? args[1] : "";
        DomXmlProcessor p = new DomXmlProcessor(args[0], xsd);
        if (!Util.isXmlIsValid(p.fileName, p.xsd)) {
            return;
        }
        p.parseFile();
        Collections.sort(p.flowers.getFlowers());
        p.saveFile("output.dom.xml");
    }

}
