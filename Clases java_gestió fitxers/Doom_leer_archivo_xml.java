package ad.t1.ifc32b.xml.XMLDOM;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.imageio.IIOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        Path path = Path.of("ficheros/ms.xml");
        File xml = path.toFile();
        try {
            /*PRIMEROS PASOS DE CONFIGURACIÓN*/
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xml);
            /*TRATAR EL FICHERO*/
            NodeList listaInicial = document.getElementsByTagName("Tests").item(0).getChildNodes();
            for (int i = 0; i < listaInicial.getLength(); i++) {
                Node node = listaInicial.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    switch (node.getNodeName()) {
                        case "Test":
                            String id = node.getAttributes().getNamedItem("TestId").getNodeValue();
                            String type = node.getAttributes().getNamedItem("TestType").getNodeValue();
                            System.out.print(node.getNodeName() + "\t-\t" + id + "\t-\t" + type);
                            System.out.println();
                            NodeList listaHijos = node.getChildNodes();
                            for (int j = 0; j < listaHijos.getLength(); j++) {
                                Node hijo = listaHijos.item(j);
                                if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                                    switch (hijo.getNodeName()) {
                                        case "Name":
                                        case "CommandLine":
                                            System.out.println("\t" + hijo.getNodeName() + "-> " + hijo.getTextContent());
                                            break;
                                    }
                                }
                            }
                            break;
                        default:
                            System.out.println(node.getNodeType());
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(-1);
        }

    }
}
