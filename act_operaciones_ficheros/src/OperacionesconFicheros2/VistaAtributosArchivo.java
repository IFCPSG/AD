package OperacionesconFicheros2;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VistaAtributosArchivo extends JFrame {
    private JTextField textFieldName, textFieldExtension, textFieldPath, textFieldSize, textFieldLastModified;

    public VistaAtributosArchivo() {
        super("Visor de Atributos de Archivo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton btnSelectFile = new JButton("Seleccionar Archivo o Directorio");
        btnSelectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFileSelector();
            }
        });

        // Campos de texto para mostrar los atributos
        textFieldName = new JTextField();
        textFieldExtension = new JTextField();
        textFieldPath = new JTextField();
        textFieldSize = new JTextField();
        textFieldLastModified = new JTextField();

        // Deshabilitar la edición de los campos de texto
        textFieldName.setEditable(false);
        textFieldExtension.setEditable(false);
        textFieldPath.setEditable(false);
        textFieldSize.setEditable(false);
        textFieldLastModified.setEditable(false);

        // Añadir componentes al panel
        panel.add(btnSelectFile);
        panel.add(new JLabel("Nombre:"));
        panel.add(textFieldName);
        panel.add(new JLabel("Formato/Extensión:"));
        panel.add(textFieldExtension);
        panel.add(new JLabel("Ruta Absoluta:"));
        panel.add(textFieldPath);
        panel.add(new JLabel("Tamaño:"));
        panel.add(textFieldSize);
        panel.add(new JLabel("Última Modificación:"));
        panel.add(textFieldLastModified);

        // Añadir panel al frame
        add(panel);

        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }

    private void showFileSelector() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            displayFileAttributes(selectedFile);
        }
    }

    private void displayFileAttributes(File file) {
        if (file != null && file.exists()) {
            textFieldName.setText(file.getName());
            textFieldPath.setText(file.getAbsolutePath());

            if (file.isFile()) {
                textFieldExtension.setText(getFileExtension(file));
                textFieldSize.setText(formatFileSize(file.length()));
            } else {
                textFieldExtension.setText("Directorio");
                textFieldSize.setText("");
            }

            textFieldLastModified.setText(formatDate(file.lastModified()));
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

    private String formatFileSize(long size) {
        String[] units = {"B", "KB", "MB", "GB", "TB"};
        int unitIndex = 0;
        double sizeInKB = size;

        while (sizeInKB > 1024 && unitIndex < units.length - 1) {
            sizeInKB /= 1024;
            unitIndex++;
        }

        return String.format("%.2f %s", sizeInKB, units[unitIndex]);
    }

    private String formatDate(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timestamp);
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VistaAtributosArchivo();
            }
        });
    }
}

