package operaciones_ficheros_streams;

import java.io.*;

public class CopiarArchivoBinario {
    public static void main(String[] args) {
        String nombreArchivo = "archivo.bin"; // Reemplaza con el nombre de tu archivo binario
        copiarArchivo(nombreArchivo);
    }

    public static void copiarArchivo(String nombreArchivo) {
        try {
            FileInputStream archivoOriginal = new FileInputStream(nombreArchivo);

            // Agregar "_COPIA" al nombre del archivo
            String nombreCopia = nombreArchivo.replaceFirst("[.][^.]+$", "_COPIA$0");

            FileOutputStream archivoCopia = new FileOutputStream(nombreCopia);

            byte[] buffer = new byte[4096]; // Tamaño del buffer (4 KB)

            int bytesLeidos;
            while ((bytesLeidos = archivoOriginal.read(buffer)) != -1) {
                archivoCopia.write(buffer, 0, bytesLeidos);
            }

            archivoOriginal.close();
            archivoCopia.close();

            System.out.println("Archivo '" + nombreArchivo + "' copiado exitosamente como '" + nombreCopia + "'.");
        } catch (FileNotFoundException e) {
            System.err.println("El archivo '" + nombreArchivo + "' no fue encontrado.");
        } catch (IOException e) {
            System.err.println("Error al copiar el archivo: " + e.getMessage());
        }
    }
}
