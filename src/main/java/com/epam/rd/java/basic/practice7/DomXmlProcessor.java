package com.epam.rd.java.basic.practice7;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.epam.rd.java.basic.practice7.XmlConstants.*;

public class DomXmlProcessor {

    private final List<Flower> flowers = new ArrayList<>();
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
                    vp.stemColour = node.getTextContent();
                    break;
                case "leafColour":
                    vp.leafColour = node.getTextContent();
                    break;
                case "aveLenFlower":
                    vp.aveLenFlower = new VisualParameters.AveLenFlower();
                    vp.aveLenFlower.value = Integer.parseInt(node.getTextContent());
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
                    Tempreture t = new Tempreture();
                    t.value = Integer.parseInt(node.getTextContent());
                    gt.tempreture = t;
                    break;
                case "lighting":
                    Lighting l = new Lighting();
                    l.lightRequiring = Lighting.LightRequiring.findValue(node.getAttributes().
                            getNamedItem("lightRequiring").getTextContent());
                    gt.lighting = l;
                    break;
                case "watering":
                    Watering w = new Watering();
                    w.value = Integer.parseInt(node.getTextContent());
                    gt.watering = w;
                    break;
                case INDENT:
                    break;
                default:
                    Logger.getGlobal().severe(ERR_UNKNOWN_NODE + node.getNodeName());
            }
        }
        flower.setGrowingTips(gt);
    }


    public static void main(String[] args) {
        DomXmlProcessor p = new DomXmlProcessor("input.xml");//args[0]);
        p.parseFile();

    }


}

