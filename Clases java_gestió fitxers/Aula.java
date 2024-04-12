import java.io.Serializable;

public class Aula implements Serializable {

    private int id;
    private String nombre;
    private int numAlumnos;

    public Aula(int id , String nombre , int numAlumnos) {
        this.id = id;
        this.nombre = nombre;
        this.numAlumnos = numAlumnos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumAlumnos() {
        return numAlumnos;
    }

    public void setNumAlumnos(int numAlumnos) {
        this.numAlumnos = numAlumnos;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numAlumnos=" + numAlumnos +
                '}';
    }
}
