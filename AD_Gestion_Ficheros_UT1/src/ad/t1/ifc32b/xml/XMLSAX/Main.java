package ad.t1.ifc32b.xml.XMLSAX;

import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("ficheros/ms.xml");
        leerXML(path);
    }

    private static void leerXML(Path path){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(new MiControladoraXML());
            reader.parse(path.toString());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }



    }
}
