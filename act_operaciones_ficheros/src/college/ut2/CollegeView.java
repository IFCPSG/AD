package college.ut2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CollegeView extends JFrame {
    private JList<String> listaAlumnos;
    private JButton btnPrimero, btnAnterior, btnSiguiente, btnUltimo, btnGuardar;
    private JFileChooser fileChooser;

    public CollegeView(DefaultListModel<String> listaModelo) {
        listaAlumnos = new JList<>(listaModelo);
        JScrollPane scrollPane = new JScrollPane(listaAlumnos);
        add(scrollPane, BorderLayout.CENTER);

        btnPrimero = new JButton("Primero");
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");
        btnUltimo = new JButton("Último");
        btnGuardar = new JButton("Guardar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnPrimero);
        buttonPanel.add(btnAnterior);
        buttonPanel.add(btnSiguiente);
        buttonPanel.add(btnUltimo);
        buttonPanel.add(btnGuardar);

        add(buttonPanel, BorderLayout.SOUTH);

        fileChooser = new JFileChooser();

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addPrimeroListener(ActionListener listener) {
        btnPrimero.addActionListener(listener);
    }

    public void addAnteriorListener(ActionListener listener) {
        btnAnterior.addActionListener(listener);
    }

    public void addSiguienteListener(ActionListener listener) {
        btnSiguiente.addActionListener(listener);
    }

    public void addUltimoListener(ActionListener listener) {
        btnUltimo.addActionListener(listener);
    }

    public void addGuardarListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public int getSelectedIndex() {
        return listaAlumnos.getSelectedIndex();
    }

    public void setSelectedIndex(int index) {
        listaAlumnos.setSelectedIndex(index);
    }

    public File mostrarDialogoGuardar() {
        int resultado = fileChooser.showSaveDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }
}
