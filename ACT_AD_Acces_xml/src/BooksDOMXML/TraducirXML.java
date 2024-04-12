package BooksDOMXML;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TraducirXML {
    public static void main(String[] args) {
        try {
            InputStream is = TraducirXML.class.getResourceAsStream("books.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);

            traducirEtiquetas(document.getDocumentElement());

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("libros.xml"));
            transformer.transform(source, result);

            System.out.println("Traducción completa. El archivo 'libros.xml' se ha creado con las etiquetas traducidas.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void traducirEtiquetas(Element element) {
        Document document = element.getOwnerDocument();
        String tagName = element.getTagName();
        String translatedTagName = traducirTag(tagName);

        // Crear un nuevo elemento con el nombre traducido
        Element newElement = document.createElement(translatedTagName);

        // Mover todos los atributos al nuevo elemento
        NamedNodeMap attributes = element.getAttributes();
        for (int i = 0; i < attributes.getLength(); i++) {
            Node attribute = attributes.item(i);
            newElement.setAttribute(attribute.getNodeName(), attribute.getNodeValue());
        }

        // Copiar todos los hijos del elemento original al nuevo elemento
        NodeList childNodes = element.getChildNodes();
        while (childNodes.getLength() > 0) {
            newElement.appendChild(childNodes.item(0));
        }

        // Reemplazar el elemento original con el nuevo elemento
        Node parent = element.getParentNode();
        parent.replaceChild(newElement, element);

        // Recursivamente traducir las etiquetas en el nuevo elemento
        childNodes = newElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode instanceof Element) {
                traducirEtiquetas((Element) childNode);
            }
        }
    }

    private static String traducirTag(String tagName) {
        Map<String, String> traducciones = new HashMap<>();
        traducciones.put("cataleg", "cataleg");
        traducciones.put("book", "llibre");
        traducciones.put("author", "autor");
        traducciones.put("title", "titol");
        traducciones.put("genre", "genere");
        traducciones.put("price", "preu");
        traducciones.put("publish_date", "data_de_publicació");  // Corrección aquí
        traducciones.put("description", "descripció");

        return traducciones.getOrDefault(tagName, tagName);
    }

}


