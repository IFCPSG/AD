package operaciones_ficheros_streams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ContadorPalabras {
    public static void main(String[] args) {
        String nombreArchivo = "nuevoArchivo.txt"; // Reemplaza con el nombre de tu archivo de texto
        int totalPalabras = contarPalabras(nombreArchivo);
        System.out.println("El archivo contiene " + totalPalabras + " palabras.");
    }

    public static int contarPalabras(String nombreArchivo) {
        int totalPalabras = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] palabras = linea.split("\\s+"); // Dividir la línea en palabras usando espacios en blanco como separadores
                totalPalabras += palabras.length;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return totalPalabras;
    }
}
