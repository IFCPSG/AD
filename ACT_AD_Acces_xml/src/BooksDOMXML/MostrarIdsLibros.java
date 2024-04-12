package BooksDOMXML;

import java.io.InputStream;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MostrarIdsLibros {
    public static void main(String[] args) {
        try {
            InputStream is = MostrarIdsLibros.class.getResourceAsStream("books.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);

            // Obtener la lista de nodos <book>
            NodeList bookNodes = document.getElementsByTagName("book");

            // Iterar a través de los nodos <book> y mostrar el atributo "id"
            for (int i = 0; i < bookNodes.getLength(); i++) {
                Node bookNode = bookNodes.item(i);
                if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                    String id = ((org.w3c.dom.Element) bookNode).getAttribute("id");
                    System.out.println("ID del libro: " + id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
