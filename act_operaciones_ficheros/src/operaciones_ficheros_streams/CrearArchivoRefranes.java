package operaciones_ficheros_streams;

import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.io.IOException;

public class CrearArchivoRefranes {

    public static void main(String[] args) {
        // Ruta del archivo
        String rutaArchivo = "refranes.txt";

        // Contenido del archivo (puedes cambiar esto según tus necesidades)
        String[] refranes = {
                "Más vale tarde que nunca.",
                "A quien madruga, Dios le ayuda.",
                "No dejes para mañana lo que puedas hacer hoy."
        };

        // Crear el archivo
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // Escribir cada refrán en una nueva línea
            for (String refran : refranes) {
                escritor.write(refran);
                escritor.newLine();  // Agregar una nueva línea después de cada refrán
            }

            System.out.println("Archivo creado exitosamente: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo: " + e.getMessage());
        }
    }
}
