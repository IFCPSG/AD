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
    private JTextField nombre;
    private JTextField apellidos;
    private JTextField fechaNacimiento;
    private JTextField ciclo;

    private CollegeController controller;

    public CollegeView(CollegeController controller) {
        this.controller = controller;
        this.controller.clone(this);

        setLayout(new BorderLayout());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listaAlumnos = new JList<>(controller.getModel());
        agregarAlumno = new JButton("Agregar alumno");

        JPanel panelAlumno = new JPanel(new GridLayout(1, 5));
        panelAlumno.add(new JLabel("Nombre:"));
        panelAlumno.add(nombre = new JTextField());
        panelAlumno.add(new JLabel("Apellidos:"));
        panelAlumno.add(apellidos = new JTextField());
        panelAlumno.add(new JLabel("Fecha de nacimiento:"));
        panelAlumno.add(fechaNacimiento = new JTextField());
        panelAlumno.add(new JLabel("Ciclo:"));
        panelAlumno.add(ciclo = new JTextField());

        add(new JScrollPane(listaAlumnos), BorderLayout.CENTER);
        add(panelAlumno, BorderLayout.NORTH);
        add(agregarAlumno, BorderLayout.SOUTH);

        agregarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarNuevoAlumno();
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
