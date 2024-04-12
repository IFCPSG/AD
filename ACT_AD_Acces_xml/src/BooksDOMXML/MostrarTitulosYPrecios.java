package BooksDOMXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MostrarTitulosYPrecios {
    public static void main(String[] args) {
        try {
            InputStream is = MostrarTitulosYPrecios.class.getResourceAsStream("books.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);

            NodeList bookNodes = document.getElementsByTagName("book");
            List<Book> books = new ArrayList<>();

            for (int i = 0; i < bookNodes.getLength(); i++) {
                Element bookElement = (Element) bookNodes.item(i);
                String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                double price = Double.parseDouble(bookElement.getElementsByTagName("price").item(0).getTextContent());
                books.add(new Book(title, price));
            }

            Collections.sort(books, Comparator.comparing(Book::getPrice));

            for (Book book : books) {
                System.out.println("Título: " + book.getTitle() + ", Precio: " + book.getPrice());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Book {
    private String title;
    private double price;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}
