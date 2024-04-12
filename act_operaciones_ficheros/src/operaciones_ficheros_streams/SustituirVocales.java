package operaciones_ficheros_streams;


    import java.io.*;
import java.util.Scanner;

    public class SustituirVocales {
        public static void main(String[] args) {
            String nombreArchivoOriginal = "refranes.txt"; // Nombre del archivo original
            String vocalSustituta;

            // Solicitar al usuario una vocal válida
            do {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Introduce una vocal (a, e, i, o, u): ");
                vocalSustituta = scanner.nextLine().toLowerCase();
            } while (!vocalSustituta.matches("[aeiou]"));

            // Generar el nombre del archivo de destino
            String nombreArchivoDestino = "refranes_CON_" + vocalSustituta + ".txt";

            try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivoOriginal));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivoDestino))) {
                String linea;

                while ((linea = br.readLine()) != null) {
                    // Sustituir todas las vocales por la vocal introducida
                    linea = linea.replaceAll("[aeiou]", vocalSustituta);
                    bw.write(linea);
                    bw.newLine();
                }

                System.out.println("Archivo generado con éxito: " + nombreArchivoDestino);
            } catch (IOException e) {
                System.err.println("Error al leer o escribir el archivo: " + e.getMessage());
            }
        }
    }


