package com.puppycrawl.tools.checkstyle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class XMLParser {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java XMLParser <xmlFilePath> <moduleName>");
            System.exit(1);
        }

        String xmlFilePath = args[0];
        String moduleName = args[1];

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(xmlFilePath));

        NodeList nodeList = doc.getElementsByTagName("source");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                if ("Example1-config".equals(elem.getAttribute("id"))) {
                    String content = elem.getTextContent().trim();
                    saveConfigToFile(moduleName, content);
                    break;
                }
            }
        }
    }

    private static void saveConfigToFile(String moduleName, String content) throws Exception {
        String dirPath = "../" + moduleName + "/default";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File configFile = new File(dirPath, "config.xml");
        try (PrintWriter writer = new PrintWriter(new FileWriter(configFile))) {
            writer.println("<!DOCTYPE module PUBLIC \"-//Checkstyle//DTD Checkstyle Configuration 1.3//EN\" \"https://checkstyle.org/dtds/configuration_1_3.dtd\">");
            writer.println("<module name=\"Checker\">");
            writer.println("    <module name=\"TreeWalker\">");
            writer.println(content);
            writer.println("    </module>");
            writer.println("</module>");
        }
    }
}
