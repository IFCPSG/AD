package college.ut2;

import java.util.Date;

public class Alumno {
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String ciclo;

    public Alumno(String nombre, String apellidos, Date fechaNacimiento, String ciclo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.ciclo = ciclo;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos + " - " + ciclo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
// Getters y setters según sea necesario
}
