package college.ut2;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollegeModel1 extends AbstractListModel<Alumno> {
    private List<Alumno> alumnos;

    public CollegeModel1() {
        alumnos = new ArrayList<>();
    }

    public void addAlumno(Alumno alumno) {
        int index = alumnos.size();
        alumnos.add(alumno);
        fireIntervalAdded(this, index, index);
    }

    public void removeAlumno(int index) {
        alumnos.remove(index);
        fireIntervalRemoved(this, index, index);
    }

    public void removeAllAlumnos() {
        int size = alumnos.size();
        alumnos.clear();
        fireIntervalRemoved(this, 0, size - 1);
    }

    public Alumno getAlumno(int index) {
        return alumnos.get(index);
    }

    @Override
    public int getSize() {
        return alumnos.size();
    }

    @Override
    public Alumno getElementAt(int index) {
        return alumnos.get(index);
    }
}
