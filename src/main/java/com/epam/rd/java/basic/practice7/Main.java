package com.epam.rd.java.basic.practice7;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Logger;


public final class Main {

    public static void main(final String[] args) {
        DomXmlProcessor.main(args);

    }

//    private static boolean validateXMLWithXSD(InputStream xml, InputStream xsd) {
//        try
//        {
//            SchemaFactory factory =
//                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
//            Schema schema = factory.newSchema(new StreamSource(xsd));
//            Validator validator = schema.newValidator();
//            validator.validate(new StreamSource(xml));
//            return true;
//        }
//        catch(Exception ex)
//        {
//            return false;
//        }
//    }

//    public static String getInput(String fileName) {
//        StringBuilder sb = new StringBuilder();
//        try {
//            Scanner scanner = new Scanner(new File(fileName), "utf-8");
//            while (scanner.hasNextLine()) {
//                sb.append(scanner.nextLine()).append(System.lineSeparator());
//            }
//            scanner.close();
//            return sb.toString().trim();
//        } catch (IOException ex) {
//            Logger.getGlobal().severe(ex.getMessage());
//        }
//        return sb.toString();
//    }

}
