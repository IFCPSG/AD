package OperacionesconFicheros2;

import java.io.File;

public class ListarNombresDirectorio {

    public static void main(String[] args) {
        // Ruta del directorio a listar
        String rutaDirectorio = "C:/Users/professor/Desktop/MÒDULS DAM/Llenguatjes de marques i sistemes de gestió de la informació/Unitats/UT2. Utilització de llenguatges de marques en entorns web/HTML5";
        // Cambia esto con la ruta adecuada

        // Llamada al método para listar los nombres
        listarNombresDirectorio(new File(rutaDirectorio), 0);
    }

    private static void listarNombresDirectorio(File directorio, int nivel) {
        if (directorio.isDirectory()) {
            // Mostrar el nombre del directorio actual
            System.out.println(getIndentation(nivel) + "/" + directorio.getName());

            // Listar archivos y directorios dentro del directorio actual
            File[] archivos = directorio.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    listarNombresDirectorio(archivo, nivel + 1); // Llamada recursiva para los subdirectorios
                }
            }
        } else {
            // Mostrar el nombre del archivo
            System.out.println(getIndentation(nivel) + directorio.getName());
        }
    }

    private static String getIndentation(int nivel) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < nivel; i++) {
            indentation.append("  "); // Dos espacios por nivel
        }
        return indentation.toString();
    }
}

