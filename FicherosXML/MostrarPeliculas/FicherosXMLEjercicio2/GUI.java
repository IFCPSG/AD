package FicherosXMLEjercicio2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUI extends JFrame {
    private JTextArea areaTexto;

    public GUI() {
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        getContentPane().add(scrollPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void mostrarPeliculas(List<Pelicula> peliculas) {
        if (peliculas.isEmpty()) {
            areaTexto.setText("No hay películas para mostrar.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Pelicula pelicula : peliculas) {
                sb.append("Título: ").append(pelicula.getTitulo()).append("\n");
                sb.append("Fecha: ").append(pelicula.getFecha()).append("\n");
                sb.append("Género: ").append(pelicula.getGenero()).append("\n");
                sb.append("Sinopsis: ").append(pelicula.getSinopsis()).append("\n");
                sb.append("Actores Principales: ").append(pelicula.getActoresPrincipales()).append("\n");
                sb.append("\n");
            }
            areaTexto.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();

            JFileChooser fileChooser = new JFileChooser();
            int seleccion = fileChooser.showOpenDialog(null);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
                List<Pelicula> peliculas = ImportadorXML.importarDesdeXML(rutaArchivo);
                gui.mostrarPeliculas(peliculas);
            }
        });
    }
}
