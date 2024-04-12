package BooksDOMXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MostrarLibrosPorGenero {
    public static void main(String[] args) {
        try {
            InputStream is = MostrarLibrosPorGenero.class.getResourceAsStream("books.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);

            NodeList bookNodes = document.getElementsByTagName("book");
            Map<String, StringBuilder> generosYLibros = new HashMap<>();

            for (int i = 0; i < bookNodes.getLength(); i++) {
                Element bookElement = (Element) bookNodes.item(i);
                String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                String genre = bookElement.getElementsByTagName("genre").item(0).getTextContent();

                generosYLibros.computeIfAbsent(genre, k -> new StringBuilder()).append(title).append(", ");
            }

            for (Map.Entry<String, StringBuilder> entry : generosYLibros.entrySet()) {
                String genre = entry.getKey();
                String libros = entry.getValue().toString();
                System.out.println("Género: " + genre);
                System.out.println("Libros: " + libros.substring(0, libros.length() - 2)); // Elimina la última coma y espacio
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

