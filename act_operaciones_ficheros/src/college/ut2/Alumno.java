package college.ut2;

public class Alumno {
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String ciclo;

    public Alumno(String nombre, String apellidos, String fechaNacimiento, String ciclo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.ciclo = ciclo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCiclo() {
        return ciclo;
    }
}
