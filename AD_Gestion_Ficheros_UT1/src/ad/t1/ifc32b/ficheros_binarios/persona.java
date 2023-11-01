package ad.t1.ifc32b.ficheros_binarios;

import java.io.Serializable;

public class persona implements Serializable {
    private int id;
    private String nombre;
    private int edad;
    private String dirección;

    public persona(int id, String nombre, int edad, String dirección) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.dirección = dirección;
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

    public int getEdad() {

        return edad;
    }

    public void setEdad(int edad) {

        this.edad = edad;
    }

    public String getDirección() {

        return dirección;
    }

    public void setDirección(String dirección) {

        this.dirección = dirección;
    }

    @Override
    public String toString() {
        return "persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", dirección='" + dirección + '\'' +
                '}';


    }
}
