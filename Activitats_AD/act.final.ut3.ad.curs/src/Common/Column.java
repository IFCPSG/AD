package Common;

public class Column {
    private String nombre;
    private String tipo;
    private int longitud;
    private boolean allowNull;

    // Constructor
    public Column(String nombre, String tipo, int longitud, boolean allowNull) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.longitud = longitud;
        this.allowNull = allowNull;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    // Método para generar la representación de la columna en la creación de una nueva tabla
    public String generarSQL() {
        String nullable = allowNull ? "" : " NOT NULL";
        String longitudSQL = (longitud > 0) ? "(" + longitud + ")" : "";
        return nombre + " " + tipo + longitudSQL + nullable;
    }

    // Método para imprimir información de la columna
    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Tipo: " + tipo);
        System.out.println("Longitud: " + longitud);
        System.out.println("Permite nulos: " + allowNull);
    }
}