package ad.t1.ifc32b.xml.XMLDOM;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        // leerXML();
        escribirXML();

    }

    private static void leerXML() {
        Path path = Path.of("ficheros/ms.xml");
        File xml = path.toFile();
        /*PRIMEROS PASOS DE CONFIGURACIÓN*/
        DocumentBuilder builder = createBuilder();
        Document document = null;
        try {
            document = builder.parse(xml);
        } catch (IOException | SAXException ex) {
            System.err.println("Error al crear el Document");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }

        /*TRATAR EL FICHERO*/
        NodeList listaInicial = document.getElementsByTagName("Tests").item(0).getChildNodes();
        switchElement(listaInicial);

    }

    private static void switchElement(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                switch (node.getNodeName()) {
                    case "Test":
                        String id = node.getAttributes().getNamedItem("TestId").getNodeValue();
                        String type = node.getAttributes().getNamedItem("TestType").getNodeValue();
                        System.out.print(node.getNodeName() + "\t-\t" + id + "\t-\t" + type);
                        System.out.println();
                        NodeList listaHijos = node.getChildNodes();
                        switchElement(listaHijos);
                        break;
                    case "Name":
                    case "CommandLine":
                        System.out.println("\t" + node.getNodeName() + "-> " + node.getTextContent());
                        break;
                    case "Input":
                        System.out.println("\t\t" + node.getNodeName() + ":" + node.getTextContent() + " -> ");
                        break;
                    case "Output":
                        System.out.println(node.getNodeName() + ":" + node.getTextContent());
                        break;

                }
            }
        }

    }

    private static void escribirXML() {

        DocumentBuilder builder = createBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null , null , null);
        document.setXmlVersion("1.0");
        document.setXmlStandalone(true);

        Element alumnos = document.createElement("Alumnos");
        document.appendChild(alumnos);

        Element alumno = document.createElement("Alumno");
        alumno.setAttribute("nombre" , "Tomeu");
        alumno.setAttribute("edad" , "25");
        Element direccion = document.createElement("direccion");
        direccion.setTextContent("C/ Hala Madrid 1-2");
        Element telefono = document.createElement("telefono");
        telefono.setTextContent("666666666");
        alumno.appendChild(direccion);
        alumno.appendChild(telefono);
        alumnos.appendChild(alumno);

        Source origen = new DOMSource(document);
        Result result = new StreamResult(new File("ficheros/alumnos.xml"));
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException ex) {
            System.err.println("Error al crear el transformer");
            System.err.println(ex.getMessage());
            System.exit(-3);
        }
        try {
            transformer.transform(origen , result);
        } catch (TransformerException ex) {
            System.err.println("Error al transformar el origen en el destino");
            System.err.println(ex.getMessage());
            System.exit(-3);
        }
    }

    private static DocumentBuilder createBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.err.println("Error al crear el DocumentBuilder");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        return builder;
    }
}

/*<alumnos>
  <alumno nombre="Tomeu" edad="25"
      <direccion>C/ Hala Madrid 1-2</direccion>
      <telefono>666666666</telefono>
  </alumno>
</alumnos>*/
