package college.ut2;

import java.io.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

public class CollegeModel1 {
    private ArrayList<Alumno> alumnos;
    private DefaultListModel<String> listaModelo;

    public CollegeModel1() {
        alumnos = new ArrayList<>();
        listaModelo = new DefaultListModel<>();
    }

    public void agregarAlumno(Alumno alumno) {
        if (alumno != null) {
            alumnos.add(alumno);
            actualizarListaModelo();
        } else {
            System.out.println("Error: No se puede agregar un alumno nulo.");
        }
    }

    public DefaultListModel<String> getListaModelo() {
        return listaModelo;
    }

    private void actualizarListaModelo() {
        listaModelo.clear();
        for (Alumno alumno : alumnos) {
            listaModelo.addElement(alumno.getNombre() + " " + alumno.getApellidos());
        }
    }

    public void guardarEnArchivo(File archivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(alumnos);
            System.out.println("Información de alumnos guardada correctamente en el archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }

    public void cargarDesdeArchivo(File archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            alumnos = (ArrayList<Alumno>) ois.readObject();
            actualizarListaModelo();
            System.out.println("Información de alumnos cargada correctamente desde el archivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar desde el archivo: " + e.getMessage());
        }
    }
}
