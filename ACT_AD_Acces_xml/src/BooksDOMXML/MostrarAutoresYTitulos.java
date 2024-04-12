package BooksDOMXML;

import java.io.InputStream;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MostrarAutoresYTitulos {
    public static void main(String[] args) {
        try {
            InputStream is = MostrarAutoresYTitulos.class.getResourceAsStream("books.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);

            // Obtener la lista de nodos <book>
            NodeList bookNodes = document.getElementsByTagName("book");

            for (int i = 0; i < bookNodes.getLength(); i++) {
                Node bookNode = bookNodes.item(i);
                if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                    // Obtener el autor y el título de cada libro
                    NodeList authorNodes = ((org.w3c.dom.Element) bookNode).getElementsByTagName("author");
                    NodeList titleNodes = ((org.w3c.dom.Element) bookNode).getElementsByTagName("title");

                    if (authorNodes.getLength() > 0 && titleNodes.getLength() > 0) {
                        String author = authorNodes.item(0).getTextContent();
                        String title = titleNodes.item(0).getTextContent();
                        System.out.println("Autor: " + author + ", Título: " + title);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
