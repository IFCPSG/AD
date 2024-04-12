package operaciones_ficheros_streams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CrearArchivoTexto {
    public static void main(String[] args) {
        String nombreArchivo = "nuevoArchivo.txt"; // Nombre del nuevo archivo de texto
        String contenido = "Hola, este es un nuevo archivo de texto. Espero que te sea útil.";

        crearArchivoTexto(nombreArchivo, contenido);
        System.out.println("Se ha creado el archivo: " + nombreArchivo);
    }

    public static void crearArchivoTexto(String nombreArchivo, String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            // Escribir el contenido en el archivo
            bw.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }
}
