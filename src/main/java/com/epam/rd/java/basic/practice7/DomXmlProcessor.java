package com.epam.rd.java.basic.practice7;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.logging.Logger;

public class DomXmlProcessor {

    private static final String ERR_UNKNOWN_NODE = "Unknown XML node: ";

    private final Flowers flowers;
    private final String fileName;
    private Document xmlDoc;


    public DomXmlProcessor(String fileName) {
        this.fileName = fileName;
        flowers = new Flowers();
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
            flowers.flowers.add(parse(f));
        }
    }

    private Flowers.Flower parse(NodeList nodeList) {
        Flowers.Flower flower = new Flowers.Flower();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            switch (node.getNodeName()) {
                case "name":
                    flower.name = node.getTextContent();
                    break;
                case "soil":
                    flower.soil = Flowers.Flower.Soil.findValue(node.getTextContent());
                    break;
                case "origin":
                    flower.origin = node.getTextContent();
                    break;
                case "visualParameters":
                    setVisualParameters(flower, node.getChildNodes());
                    break;
                case "growingTips":
                    setGrowingTips(flower, node.getChildNodes());
                    break;
                case "multiplying":
                    flower.multiplying = Flowers.Flower.Multiplying.findValue(node.getTextContent());
                    break;
                case "#text":
                    break;
                default:
                    Logger.getGlobal().severe(ERR_UNKNOWN_NODE + node.getNodeName());
            }
        }
        return flower;
    }

    private void setVisualParameters(Flowers.Flower flower, NodeList nodes) {
        Flowers.Flower.VisualParameters vp = new Flowers.Flower.VisualParameters();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            switch (node.getNodeName()) {
                case "stemColour":
                    vp.stemColour = node.getTextContent();
                    break;
                case "leafColour":
                    vp.leafColour = node.getTextContent();
                    break;
                case "aveLenFlower":
                    vp.aveLenFlower = new Flowers.Flower.VisualParameters.AveLenFlower();
                    vp.aveLenFlower.value = Integer.parseInt(node.getTextContent());
                    break;
                case "#text":
                    break;
                default:
                    Logger.getGlobal().severe(ERR_UNKNOWN_NODE + node.getNodeName());
            }
        }
        flower.visualParameters = vp;
    }

    private void setGrowingTips(Flowers.Flower flower, NodeList nodes) {
        Flowers.Flower.GrowingTips gt = new Flowers.Flower.GrowingTips();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            switch (node.getNodeName()) {
                case "tempreture":
                    Flowers.Flower.GrowingTips.Temperature t = new Flowers.Flower.GrowingTips.Temperature();
                    t.value = Integer.parseInt(node.getTextContent());
                    gt.temperature = t;
                    break;
                case "lighting":
                    Flowers.Flower.GrowingTips.Lighting l = new Flowers.Flower.GrowingTips.Lighting();
                    l.lightRequiring = Flowers.Flower.GrowingTips.Lighting.LightRequiring
                            .findValue(node.getAttributes().getNamedItem("lightRequiring")
                                    .getTextContent());
                    gt.lighting = l;
                    break;
                case "watering":
                    Flowers.Flower.GrowingTips.Watering w = new Flowers.Flower.GrowingTips.Watering();
                    w.value = Integer.parseInt(node.getTextContent());
                    gt.watering = w;
                    break;
                case "#text":
                    break;
                default:
                    Logger.getGlobal().severe(ERR_UNKNOWN_NODE + node.getNodeName());
            }
        }
        flower.growingTips = gt;
    }


    public static void main(String[] args) {
        DomXmlProcessor p = new DomXmlProcessor("input.xml");//args[0]);
        p.parseFile();

    }


}
