package college.ut2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CollegeController {
    private static CollegeModel1 modelo;
    private static CollegeView vista;

    public CollegeController(CollegeModel1 modelo, CollegeView vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.addPrimeroListener(new NavegarListener(NavegarListener.Primero));
        vista.addAnteriorListener(new NavegarListener(NavegarListener.Anterior));
        vista.addSiguienteListener(new NavegarListener(NavegarListener.Siguiente));
        vista.addUltimoListener(new NavegarListener(NavegarListener.Ultimo));
        vista.addGuardarListener(new GuardarListener());

        // Configurar otros listeners o eventos según sea necesario
    }

    public void agregarAlumno() {
        // Lógica para agregar un alumno (como en el ejemplo anterior)
    }

    private static class NavegarListener implements ActionListener {
        private static final int Primero = 0;
        private static final int Anterior = 1;
        private static final int Siguiente = 2;
        private static final int Ultimo = 3;

        private int operacion;

        public NavegarListener(int operacion) {
            this.operacion = operacion;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = vista.getSelectedIndex();
            int lastIndex = modelo.getListaModelo().getSize() - 1;

            switch (operacion) {
                case Primero:
                    if (lastIndex >= 0) {
                        vista.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay alumnos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case Anterior:
                    if (selectedIndex > 0) {
                        vista.setSelectedIndex(selectedIndex - 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya estás en el primer alumno.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case Siguiente:
                    if (selectedIndex < lastIndex) {
                        vista.setSelectedIndex(selectedIndex + 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya estás en el último alumno.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case Ultimo:
                    if (lastIndex >= 0) {
                        vista.setSelectedIndex(lastIndex);
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay alumnos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
            }
        }
    }

    private class GuardarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            File archivo = vista.mostrarDialogoGuardar();
            if (archivo != null) {
                modelo.guardarEnArchivo(archivo);
            } else {
                JOptionPane.showMessageDialog(null, "Operación de guardar cancelada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        CollegeModel1 modelo = new CollegeModel1();
        CollegeView vista = new CollegeView(modelo.getListaModelo());
        CollegeController controlador = new CollegeController(modelo, vista);

        // Agregar algunos alumnos de ejemplo
        controlador.agregarAlumno();
        controlador.agregarAlumno();
    }
}
