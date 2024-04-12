package college.ut2;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CollegeView extends JFrame implements CollegeChangeListener, ListDataListener {
    JList<Alumno> listaAlumnos;
    private JButton agregarAlumno;
    private JButton primeroButton;
    private JButton anteriorButton;
    private JButton siguienteButton;
    private JButton ultimoButton;
    private JTextField nombre;
    private JTextField apellidos;
    private JTextField fechaNacimiento;
    private JTextField ciclo;

    private CollegeController controller;

    public CollegeView(CollegeController controller) {
        this.controller = controller;
        this.controller.addChangeListener(this);

        setLayout(new BorderLayout());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listaAlumnos = new JList<>(controller.getModel());
        agregarAlumno = new JButton("Agregar alumno");
        primeroButton = new JButton("Primero");
        anteriorButton = new JButton("Anterior");
        siguienteButton = new JButton("Siguiente");
        ultimoButton = new JButton("Último");

        JPanel panelAlumno = new JPanel(new GridLayout(1, 5));
        panelAlumno.add(new JLabel("Nombre:"));
        panelAlumno.add(nombre = new JTextField());
        panelAlumno.add(new JLabel("Apellidos:"));
        panelAlumno.add(apellidos = new JTextField());
        panelAlumno.add(new JLabel("Fecha de nacimiento:"));
        panelAlumno.add(fechaNacimiento = new JTextField());
        panelAlumno.add(new JLabel("Ciclo:"));
        panelAlumno.add(ciclo = new JTextField());

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(primeroButton);
        panelBotones.add(anteriorButton);
        panelBotones.add(siguienteButton);
        panelBotones.add(ultimoButton);

        add(new JScrollPane(listaAlumnos), BorderLayout.CENTER);
        add(panelAlumno, BorderLayout.NORTH);
        add(agregarAlumno, BorderLayout.SOUTH);
        add(panelBotones, BorderLayout.SOUTH);

        agregarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNuevoAlumno();
            }
        });

        primeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPrimerAlumno();
            }
        });

        anteriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAnteriorAlumno();
            }
        });

        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSiguienteAlumno();
            }
        });

        ultimoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarUltimoAlumno();
            }
        });
    }

    private void agregarNuevoAlumno() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = formatter.parse(fechaNacimiento.getText());

            Alumno nuevoAlumno = new Alumno(nombre.getText(), apellidos.getText(), date, ciclo.getText());
            controller.agregarAlumno(nuevoAlumno);

            // Limpiar los campos después de agregar un alumno
            nombre.setText("");
            apellidos.setText("");
            fechaNacimiento.setText("");
            ciclo.setText("");
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(this, "Error al agregar el alumno. Verifica los datos.");
        }
    }

    private void mostrarPrimerAlumno() {
        if (!controller.getModel().isEmpty()) {
            listaAlumnos.setSelectedIndex(0);
        }
    }

    private void mostrarAnteriorAlumno() {
        int selectedIndex = listaAlumnos.getSelectedIndex();
        if (selectedIndex > 0) {
            listaAlumnos.setSelectedIndex(selectedIndex - 1);
        }
    }

    private void mostrarSiguienteAlumno() {
        int selectedIndex = listaAlumnos.getSelectedIndex();
        int lastIndex = listaAlumnos.getModel().getSize() - 1;
        if (selectedIndex < lastIndex) {
            listaAlumnos.setSelectedIndex(selectedIndex + 1);
        }
    }

    private void mostrarUltimoAlumno() {
        int lastIndex = listaAlumnos.getModel().getSize() - 1;
        if (lastIndex >= 0) {
            listaAlumnos.setSelectedIndex(lastIndex);
        }
    }

    @Override
    public void onCollegeDataChange(java.util.List<Alumno> alumnos) {

    }

    @Override
    public void onCollegeDataChange(List alumnos) {
        // Actualizar la vista cuando cambie el modelo
        listaAlumnos.setModel(controller.getModel());
    }

    @Override
    public void intervalAdded(ListDataEvent e) {

    }

    @Override
    public void intervalRemoved(ListDataEvent e) {

    }

    @Override
    public void contentsChanged(ListDataEvent e) {

    }
}
