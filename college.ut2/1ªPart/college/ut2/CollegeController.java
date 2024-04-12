package college.ut2;

import javax.swing.*;
import java.util.List;

public class CollegeController {
    private CollegeModel1 model;
    private CollegeView view;

    public CollegeController() {
        model = new CollegeModel1();
        view = new CollegeView(this);

        // Establecer la vista como observador del modelo
        view.listaAlumnos.getModel().addListDataListener(view);


        // Hacer visible la vista
        view.setVisible(true);
    }

    public void agregarAlumno(Alumno alumno) {
        model.addAlumno(alumno);
    }

    public CollegeModel1 getModel() {
        return model;
    }

    public static void main(String[] args) {
        // Iniciar la aplicación desde el controlador
        SwingUtilities.invokeLater(() -> new CollegeController());
    }

    public void clone(CollegeView collegeView) {
    }
}
