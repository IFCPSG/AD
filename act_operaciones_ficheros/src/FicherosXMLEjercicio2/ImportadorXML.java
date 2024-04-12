package FicherosXMLEjercicio2;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImportadorXML {

    public static List<Pelicula> importarDesdeXML(String rutaArchivo) {
        List<Pelicula> peliculas = new ArrayList<>();

        try {
            File archivoXML = new File(rutaArchivo);

            if (!archivoXML.exists()) {
                throw new Exception("El archivo XML no existe.");
            }

            SAXReader reader = new SAXReader();
            Document document = reader.read(archivoXML);

            Element root = document.getRootElement();
            List<Element> elementosPelicula = root.elements("pelicula");

            for (Element elementoPelicula : elementosPelicula) {
                String titulo = elementoPelicula.elementText("titulo");
                String fecha = elementoPelicula.elementText("fecha");
                String genero = elementoPelicula.elementText("genero");
                String sinopsis = elementoPelicula.elementText("sinopsis");
                String actoresPrincipales = elementoPelicula.elementText("actoresPrincipales");

                Pelicula pelicula = new Pelicula(titulo, fecha, genero, sinopsis, actoresPrincipales);
                peliculas.add(pelicula);
            }

        } catch (Exception e) {
            mostrarError("Error al importar desde XML: " + e.getMessage());
        }

        return peliculas;
    }

    private static void mostrarError(String mensaje) {
        System.err.println(mensaje);
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
