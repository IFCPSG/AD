package FicherosXMLEjercicio1;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportadorXML {

    public static void exportarRecetasAXML(List<Receta> recetas, String rutaArchivo) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("recetas");

        for (Receta receta : recetas) {
            Element recetaElement = root.addElement("receta");
            recetaElement.addElement("nombre").setText(receta.getNombre());
            recetaElement.addElement("descripcion").setText(receta.getDescripcion());
            recetaElement.addElement("ingredientes").setText(receta.getIngredientes());
        }

        try (FileWriter writer = new FileWriter(rutaArchivo)) {
            document.write(writer);
            System.out.println("Información de recetas exportada a XML correctamente.");
        } catch (IOException e) {
            System.err.println("Error al exportar a XML: " + e.getMessage());
        }
    }
}
