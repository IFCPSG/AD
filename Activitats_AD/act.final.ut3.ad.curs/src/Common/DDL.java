package Common;

        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.SQLException;

// Clase DDL
public class DDL {
    private Connection conexion;  // Necesitas una conexión a la base de datos para ejecutar las consultas

    // Constructor
    public DDL(Connection conexion) {
        this.conexion = conexion;
    }

    // Función para crear una tabla
    public void crearTabla(String nombreTabla, Column[] columnas) {
        try {
            StringBuilder query = new StringBuilder("CREATE TABLE " + nombreTabla + " (");

            for (int i = 0; i < columnas.length; i++) {
                query.append(columnas[i].generarSQL());

                if (i < columnas.length - 1) {
                    query.append(", ");
                }
            }

            query.append(")");

            try (PreparedStatement statement = conexion.prepareStatement(query.toString())) {
                statement.executeUpdate();
                System.out.println("Tabla " + nombreTabla + " creada con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    // Función para crear ambas tablas
    public void crearAmbasTablas() {
        // Definir las columnas para las tablas Persona y Direccion
        Column[] columnasPersona = {
                new Column("id", "INT", 0, false),
                new Column("nombre", "VARCHAR", 255, false),
                new Column("telefono", "VARCHAR", 15, true),
                new Column("direccion", "VARCHAR", 255, true)
                // Puedes agregar más columnas según tus necesidades
        };

        Column[] columnasDireccion = {
                new Column("id", "INT", 0, false),
                new Column("idPersona", "INT", 0, false),
                new Column("direccion", "VARCHAR", 255, true)
                // Puedes agregar más columnas según tus necesidades
        };

        // Crear las tablas
        crearTabla("Persona", columnasPersona);
        crearTabla("Direccion", columnasDireccion);

        // Añadir la FK entre Dirección y Persona
        // (Debes implementar la lógica para añadir la clave foránea según tu sistema de base de datos)

        System.out.println("Ambas tablas creadas con éxito.");
    }

    // Función para actualizar una tabla
    public void actualizarTabla(String nombreTabla, Column[] nuevasColumnas) {
        try {
            StringBuilder query = new StringBuilder("ALTER TABLE " + nombreTabla + " ");

            for (int i = 0; i < nuevasColumnas.length; i++) {
                query.append("ADD " + nuevasColumnas[i].generarSQL());

                if (i < nuevasColumnas.length - 1) {
                    query.append(", ");
                }
            }

            try (PreparedStatement statement = conexion.prepareStatement(query.toString())) {
                statement.executeUpdate();
                System.out.println("Tabla " + nombreTabla + " actualizada con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar la tabla: " + e.getMessage());
        }
    }

    // Función para eliminar una tabla
    public void eliminarTabla(String nombreTabla) {
        try {
            String query = "DROP TABLE " + nombreTabla;

            try (PreparedStatement statement = conexion.prepareStatement(query)) {
                statement.executeUpdate();
                System.out.println("Tabla " + nombreTabla + " eliminada con éxito.");
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar la tabla: " + e.getMessage());
        }
    }

    // Función para eliminar ambas tablas
    public void eliminarAmbasTablas() {
        eliminarTabla("Direccion");
        eliminarTabla("Persona");

        // (Debes implementar la lógica para eliminar la clave foránea entre Dirección y Persona)

        System.out.println("Ambas tablas eliminadas con éxito.");
    }
}
