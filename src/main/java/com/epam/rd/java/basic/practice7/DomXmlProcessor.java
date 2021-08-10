package com.epam.rd.java.basic.practice7;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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

import static com.epam.rd.java.basic.practice7.XmlConstants.ERR_UNKNOWN_NODE;
import static com.epam.rd.java.basic.practice7.XmlConstants.INDENT;

public class DomXmlProcessor {

    private final Flowers flowers = new Flowers();
    private final String fileName;
    private Document xmlDoc;


    public DomXmlProcessor(String fileName) {
        this.fileName = fileName;
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
                case "name":
                    flower.setName(node.getTextContent());
                    break;
                case "soil":
                    flower.setSoil(Soil.findValue(node.getTextContent()));
                    break;
                case "origin":
                    flower.setOrigin(node.getTextContent());
                    break;
                case "visualParameters":
                    setVisualParameters(flower, node.getChildNodes());
                    break;
                case "growingTips":
                    setGrowingTips(flower, node.getChildNodes());
                    break;
                case "multiplying":
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
                case "stemColour":
                    vp.setStemColour(node.getTextContent());
                    break;
                case "leafColour":
                    vp.setLeafColour(node.getTextContent());
                    break;
                case "aveLenFlower":
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
                case "tempreture":
                    gt.setTempreture(new Tempreture(Integer.parseInt(node.getTextContent())));
                    break;
                case "lighting":
                    Lighting.LightRequiring lightRequiring =
                            Lighting.LightRequiring.findValue(node.getAttributes().
                                    getNamedItem("lightRequiring").getTextContent());
                    gt.setLighting(new Lighting(lightRequiring));
                    break;
                case "watering":
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
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();
        Element root = doc.createElement("flowers");
        root.setAttribute("xmlns:tns", "http://www.example.org/input");
        root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        root.setAttribute("xsi:schemaLocation", "http://www.example.org/input input.xsd");
        doc.appendChild(root);
        for (Flower f : flowers.getFlowers()) {
            Element flower = doc.createElement("flower");
            // append name, soil, origin
            appendChild(doc, flower, "name", f.getName());
            appendChild(doc, flower, "soil", f.getSoil().toString());
            appendChild(doc, flower, "origin", f.getOrigin());
            // append visualParameters
            Element vp = doc.createElement("visualParameters");
            appendChild(doc, vp, "stemColour", f.getVisualParameters().getStemColour());
            appendChild(doc, vp, "leafColour", f.getVisualParameters().getLeafColour());
            appendChildWithAttribute(doc, vp, "aveLenFlower",
                    String.valueOf(f.getVisualParameters().getAveLenFlower().getValue()),
                    "measure", AveLenFlower.getMeasure());
            flower.appendChild(vp);
            // append growingTips
            Element gt = doc.createElement("growingTips");
            appendChildWithAttribute(doc, gt, "tempreture",
                    String.valueOf(f.getGrowingTips().getTempreture().getValue()),
                    "measure", Tempreture.getMEASURE());
            appendChildWithAttribute(doc, gt, "lighting", "", "lightRequiring",
                    f.getGrowingTips().getLighting().getLightRequiring().toString());
            appendChildWithAttribute(doc, gt, "watering",
                    String.valueOf(f.getGrowingTips().getWatering().getValue()),
                    "measure", Watering.getMEASURE());
            flower.appendChild(gt);
            // append multiplying
            appendChild(doc, flower, "multiplying", f.getMultiplying().toString());
            // append flower to root
            root.appendChild(flower);
        }
        return doc;
    }

    public static void main(String[] args) {
        DomXmlProcessor p = new DomXmlProcessor("input.xml");//args[0]);
        p.parseFile();
        Collections.sort(p.flowers.getFlowers());
        p.saveFile("output.dom.xml");
    }


}

