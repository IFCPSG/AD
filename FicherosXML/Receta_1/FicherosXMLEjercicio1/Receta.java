package FicherosXMLEjercicio1;

public class Receta {
    private String nombre;
    private String descripcion;
    private String ingredientes;

    public Receta(String nombre, String descripcion, String ingredientes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }
}
