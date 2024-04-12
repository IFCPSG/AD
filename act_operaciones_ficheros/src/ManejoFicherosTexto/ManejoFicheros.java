package ManejoFicherosTexto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ManejoFicheros extends JFrame {

    private JTextArea textArea;
    private File selectedFile;

    public ManejoFicheros() {
        super("Manejo de Ficheros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Barra de menú
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu archivoMenu = new JMenu("Archivo");
        menuBar.add(archivoMenu);

        JMenuItem crearArchivoItem = new JMenuItem("Crear Archivo");
        JMenuItem seleccionarArchivoItem = new JMenuItem("Seleccionar Archivo");
        JMenuItem borrarArchivoItem = new JMenuItem("Borrar Archivo");

        archivoMenu.add(crearArchivoItem);
        archivoMenu.add(seleccionarArchivoItem);
        archivoMenu.add(borrarArchivoItem);

        // Área de texto
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Manejadores de eventos
        crearArchivoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearArchivo();
            }
        });

        seleccionarArchivoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarArchivo();
            }
        });

        borrarArchivoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarArchivo();
            }
        });
    }

    private void crearArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            // Crear el archivo
            try {
                if (selectedFile.createNewFile()) {
                    JOptionPane.showMessageDialog(this, "Archivo creado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "El archivo ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al crear el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            // Mostrar información sobre el archivo
            mostrarInformacionArchivo();
        }
    }

    private void borrarArchivo() {
        if (selectedFile != null) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que quieres borrar este archivo?",
                    "Confirmar Borrado",
                    JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                if (selectedFile.delete()) {
                    JOptionPane.showMessageDialog(this, "Archivo borrado exitosamente.");
                    selectedFile = null;
                    textArea.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al borrar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarInformacionArchivo() {
        if (selectedFile != null) {
            StringBuilder info = new StringBuilder();
            info.append("Nombre: ").append(selectedFile.getName()).append("\n");
            info.append("Ruta: ").append(selectedFile.getAbsolutePath()).append("\n");

            if (selectedFile.isFile()) {
                info.append("Tamaño: ").append(selectedFile.length()).append(" bytes").append("\n");
                info.append("Extensión: ").append(getFileExtension(selectedFile)).append("\n");
            }

            JOptionPane.showMessageDialog(this, info.toString(), "Información del Archivo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastDotIndex = name.lastIndexOf(".");
        if (lastDotIndex > 0) {
            return name.substring(lastDotIndex + 1);
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManejoFicheros().setVisible(true);
            }
        });
    }
}
